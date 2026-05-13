// src/llm/base.js

/**
 * Abstract base class for LLM providers
 * Defines the common interface that all LLM providers must implement
 */
export class BaseLLMProvider {
    constructor(config, providerName) {
        if (this.constructor === BaseLLMProvider) {
            throw new Error('BaseLLMProvider is abstract and cannot be instantiated directly');
        }
        this.config = config;
        this.providerName = providerName
    }

    getProviderName() {
        return this.providerName;
    }

    _createErrorResponse(error) {
        return {
            content: [{
                type: 'text',
                text: error.message
            }],
            usage: null,
            metadata: {
                provider: this.constructor.name.toLowerCase().replace('provider', ''),
                model: this.config.model,
                error: true,
                errorDetail: error.internalMessage
            }
        };
    }

    handleApiError(response, errorData) {
        if (response.status === 401) {
            return this._createErrorResponse({
                message: "I'm currently unable to assist, I'm having a problem with my API.  Try again in a few minutes or contact support@bito.ai with error code: Wingman 401.",
                internalMessage: "Authentication failed: Invalid API key"
            });
        } else if (response.status === 429) {
            return this._createErrorResponse({
                message: "I'm handling too many requests right now. Please try again in a moment.",
                internalMessage: "Rate limit exceeded"
            });
        } else if (response.status === 400) {
            return this._createErrorResponse({
                message: "Sorry, I'm having trouble processing your request.  The LLM may be inaccessible.  Try again in a few minutes or contact support@bito.ai with with error code: Wingman 400.",
                internalMessage: "Invalid request format"
            });
        } else if (response.status >= 500) {
            return this._createErrorResponse({
                message: "I'm experiencing technical difficulties, the LLM may be down. Try again in a few minutes or contact support@bito.ai with error code: Wingman 500.",
                internalMessage: "Server error"
            });
        } else {
            return this._createErrorResponse({
                message: "Sorry, I'm having trouble right now. Try again in a few minutes or contact support@bito.ai with with error code: Wingman  100.",
                internalMessage: `API error: ${response.status} - ${errorData}`
            });
        }
    }

    /**
     * Initialize the LLM provider
     */
    async initialize() {
        throw new Error('initialize() must be implemented');
    }

    /**
     * Process a request and return a standardized response
     * @param {Array} messages - Array of message objects
     * @param {Object} options - Request options
     * @returns {Promise<Object>} Standardized response object
     */
    async processRequest(messages, options) {
        throw new Error('processRequest() must be implemented');
    }

    /**
     * Convert provider-specific response to standard format
     * @param {Object} rawResponse - Raw response from the LLM
     * @returns {Object} Standardized response format
     */
    createStandardResponse(rawResponse) {
        throw new Error('createStandardResponse() must be implemented');
    }

    /**
     * Format tool calls in provider-specific format
     * @param {Object} toolCall - Tool call information
     * @returns {Object} Formatted tool call
     */
    formatToolCall(toolCall) {
        throw new Error('formatToolCall() must be implemented');
    }

    /**
     * Validate provider configuration
     * @returns {boolean} True if configuration is valid
     */
    validateConfig() {
        throw new Error('validateConfig() must be implemented');
    }
}
