// src/llm/openai.js

import OpenAI from 'openai';
import { BaseLLMProvider } from './base.js';

export class OpenAIProvider extends BaseLLMProvider {
    constructor(config) {
        super(config, 'OpenAIProvider');
        this.openai = new OpenAI();
    }

    async initialize() {
        if (!this.validateConfig()) {
            throw new Error('Invalid OpenAI configuration');
        }
    }

    validateConfig() {
        const requiredFields = ['model', 'maxTokens', 'temperature'];
        return requiredFields.every(field => this.config[field] !== undefined);
    }

    async processRequest(messages, options) {
        const openAIMessages = this._convertToOpenAIFormat(messages);
        
        const requestOptions = {
            model: this.config.model,
            messages: openAIMessages,
            temperature: options.temperature || this.config.temperature,
            max_tokens: options.maxTokens || this.config.maxTokens,
            tools: options.tools ? this._convertToolsToOpenAIFormat(options.tools) : undefined,
            tool_choice: options.toolChoice || "auto"
        };

        if (options.system) {
            openAIMessages.unshift({
                role: 'system',
                content: options.system
            });
        }

        const response = await this.openai.chat.completions.create(requestOptions);
        return this.createStandardResponse(response);
    }

    createStandardResponse(rawResponse) {
        if (!rawResponse || !rawResponse.choices || !rawResponse.choices[0]) {
            throw new Error('Invalid response format from OpenAI');
        }

        const message = rawResponse.choices[0].message;
        
        return {
            content: [
                ...this._convertContentToStandard(message.content),
                ...this._convertToolCallsToStandard(message.tool_calls)
            ],
            usage: rawResponse.usage ? {
                inputTokens: rawResponse.usage.prompt_tokens,
                outputTokens: rawResponse.usage.completion_tokens,
                totalTokens: rawResponse.usage.total_tokens
            } : null,
            metadata: {
                provider: 'openai',
                model: rawResponse.model,
                finished_reason: rawResponse.choices[0].finish_reason,
                created: rawResponse.created
            }
        };
    }

    formatToolCall(toolCall) {
        return {
            id: toolCall.id,
            function: {
                name: toolCall.name,
                arguments: JSON.stringify(toolCall.arguments)
            }
        };
    }

    _convertToOpenAIFormat(messages) {
        return messages.map(msg => ({
            role: msg.role,
            content: Array.isArray(msg.content) 
                ? this._convertComplexContentToOpenAI(msg.content)
                : msg.content,
            ...(msg.tool_call_id && { tool_call_id: msg.tool_call_id })
        }));
    }

    _convertComplexContentToOpenAI(content) {
        return content.map(item => {
            if (item.type === 'text') {
                return item.text;
            } else if (item.type === 'tool_result') {
                return `Tool result for ${item.tool_use_id}: ${item.content}`;
            }
            return JSON.stringify(item);
        }).join('\n');
    }

    _convertToolsToOpenAIFormat(tools) {
        return tools.map(tool => ({
            type: 'function',
            function: {
                name: tool.name,
                description: tool.description,
                parameters: {
                    type: 'object',
                    properties: tool.input_schema.properties,
                    required: tool.input_schema.required
                }
            }
        }));
    }

    _convertContentToStandard(content) {
        return content ? [{ type: 'text', text: content }] : [];
    }

    _convertToolCallsToStandard(toolCalls) {
        if (!toolCalls) return [];
        
        return toolCalls.map(call => ({
            type: 'tool_use',
            tool: {
                name: call.function.name,
                arguments: JSON.parse(call.function.arguments),
                id: call.id
            }
        }));
    }
}
