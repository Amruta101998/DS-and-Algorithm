import fetch from 'node-fetch';
import { BaseLLMProvider } from './base.js';

const ANTHROPIC_API_URL = 'https://api.anthropic.com/v1/messages';
const ANTHROPIC_API_VERSION = '2023-06-01';

export class AnthropicProvider extends BaseLLMProvider {
    constructor(config) {
        super(config, 'AnthropicProvider');
        this.apiKey = config.apiKey
    }

    async initialize() {
        if (!this.validateConfig()) {
            throw new Error('Invalid Anthropic configuration');
        }
    }

    validateConfig() {
        const requiredFields = ['model', 'maxTokens', 'temperature'];
        return requiredFields.every(field => this.config[field] !== undefined);
    }

    async processRequest(messages, options) {
        const cleanedMessages = this._cleanAndValidateMessages(messages);
        
        if (cleanedMessages.length === 0) {
            return this._createErrorResponse({
                message: "I couldn't process your request. Could you please rephrase your question?",
                internalMessage: "No valid messages to process"
            });
        }

        const requestBody = {
            model: this.config.model,
            messages: cleanedMessages,
            temperature: options.temperature || this.config.temperature,
            max_tokens: options.maxTokens || this.config.maxTokens,
            tools: options.tools,
            tool_choice: options.toolChoice || { type: "auto" }
        };

        // Add system message with cache control if provided
        if (options.system) {
            requestBody.system = [{
                type: 'text',
                text: options.system,
                cache_control: {
                    type: 'ephemeral'
                }
            }];
        }

        if (this.config.debug) {
            console.log('Anthropic Request Options:', {
                messageCount: cleanedMessages.length,
                messages: cleanedMessages.map(m => ({
                    role: m.role,
                    contentLength: m.content ? 
                        (Array.isArray(m.content) ? 
                            JSON.stringify(m.content).length : 
                            m.content.length) 
                        : 0
                }))
            });
        }

        try {
            const response = await fetch(ANTHROPIC_API_URL, {
                method: 'POST',
                headers: {
                    'x-api-key': this.apiKey,
                    'anthropic-version': ANTHROPIC_API_VERSION,
                    'content-type': 'application/json',
                    'anthropic-beta': 'prompt-caching-2024-07-31'
                },
                body: JSON.stringify(requestBody)
            });

            if (!response.ok) {
                const errorData = await response.text();
                return this.handleApiError(response, errorData);
            }

            const rawResponse = await response.json();
            return this.createStandardResponse(rawResponse);
        } catch (error) {
            return this._createErrorResponse({
                message: "I lost connection while processing your request. Please check your internet connection and try your query again.",
                internalMessage: "Network or service communication error"
            });
        }
    }

    _cleanAndValidateMessages(messages) {
        return messages.filter(msg => {
            // Ensure message has required properties
            if (!msg || !msg.role) return false;

            // Handle array content
            if (Array.isArray(msg.content)) {
                // Filter out empty content objects and validate each content item
                const validContent = msg.content.filter(item => {
                    if (item.type === 'text') {
                        return item.text && item.text.trim().length > 0;
                    }
                    if (item.type === 'tool_result') {
                        return item.content && item.content.trim().length > 0;
                    }
                    if (item.type === 'tool_use') {
                        return item.name && item.input;
                    }
                    return false;
                });

                if (validContent.length > 0) {
                    return { ...msg, content: validContent };
                }
                return false;
            }

            // Handle string content
            if (typeof msg.content === 'string') {
                return msg.content.trim().length > 0;
            }

            return false;
        }).map(msg => this._convertMessageFormat(msg));
    }

    _convertMessageFormat(msg) {
        // Handle string content
        if (typeof msg.content === 'string') {
            return {
                role: msg.role,
                content: [{ type: 'text', text: msg.content.trim() }]
            };
        }

        // Handle array content
        if (Array.isArray(msg.content)) {
            return {
                role: msg.role,
                content: msg.content.map(item => {
                    if (item.type === 'text') {
                        return { type: 'text', text: item.text.trim() };
                    }
                    if (item.type === 'tool_result') {
                        return {
                            type: 'tool_result',
                            tool_use_id: item.tool_use_id,
                            content: item.content
                        };
                    }
                    if (item.type === 'tool_use') {
                        return {
                            type: 'tool_use',
                            id: item.id || item.tool_use_id,
                            name: item.name,
                            input: typeof item.input === 'string' ? 
                                JSON.parse(item.input) : item.input
                        };
                    }
                    return item;
                })
            };
        }

        return msg;
    }

    createStandardResponse(rawResponse) {
        if (!rawResponse || !Array.isArray(rawResponse.content)) {
            return this._createErrorResponse({
                message: "I couldn't generate a proper response. Could you try rephrasing your question?",
                internalMessage: "Invalid response format from Anthropic"
            });
        }
    

        if (rawResponse.usage && this.config.debug) {
            console.log('Cache Usage Metrics:', {
                inputTokens: rawResponse.usage.input_tokens,
                outputTokens: rawResponse.usage.output_tokens,
                cacheCreationInputTokens: rawResponse.usage.cache_creation_input_tokens,
                cacheReadInputTokens: rawResponse.usage.cache_read_input_tokens
            });
        }

        return {
            content: rawResponse.content.map(content => {
                if (content.type === 'text') {
                    return {
                        type: 'text',
                        text: content.text
                    };
                } else if (content.type === 'tool_use') {
                    return {
                        type: 'tool_use',
                        id: content.id,
                        name: content.name,
                        input: content.input || {}
                    };
                }
                return content;
            }),
            usage: rawResponse.usage ? {
                inputTokens: rawResponse.usage.input_tokens || 0,
                outputTokens: rawResponse.usage.output_tokens || 0,
                totalTokens: (rawResponse.usage.input_tokens || 0) + (rawResponse.usage.output_tokens || 0),
                // Add cache-related metrics to the response
                cacheCreationInputTokens: rawResponse.usage.cache_creation_input_tokens || 0,
                cacheReadInputTokens: rawResponse.usage.cache_read_input_tokens || 0
            } : null,
            metadata: {
                provider: 'anthropic',
                model: rawResponse.model,
                finished_reason: rawResponse.stop_reason,
                created: rawResponse.created
            }
        };
    }


    formatToolCall(toolCall) {
        return {
            id: toolCall.id,
            function: {
                name: toolCall.name,
                arguments: typeof toolCall.input === 'string' ? 
                    toolCall.input : 
                    JSON.stringify(toolCall.input || {})
            }
        };
    }
}
