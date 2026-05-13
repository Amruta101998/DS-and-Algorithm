// src/llm/factory.js

import { AnthropicProvider } from './anthropic.js';
import { OpenAIProvider } from './openai.js';
import { BitoProvider } from './bito.js';

/**
 * Factory class for creating LLM provider instances
 */
export class LLMFactory {
  /**
   * Create an instance of an LLM provider
   * @param {string} type - Provider type ('anthropic' or 'openai')
   * @param {Object} config - Provider configuration
   * @returns {BaseLLMProvider} LLM provider instance
   */
  static createProvider(type, config) {
    // Validate required configuration first
    if (!config) {
      console.log('ANDY');
      throw new Error('Configuration is required for LLM provider');
    }

    if (!config.apiKey) {
      throw new Error(`API key not found for provider: ${type}`);
    }
    switch (type.toLowerCase()) {
      case 'anthropic':
        return new AnthropicProvider(config);
      case 'openai':
        return new OpenAIProvider(config);
      case 'bito':
        return new BitoProvider(config);
      default:
        throw new Error(`Unknown LLM provider type: ${type}`);
    }
  }

  /**
   * Get available provider types
   * @returns {Array<string>} Array of available provider types
   */
  static getAvailableProviders() {
    //return ['anthropic', 'openai'];
    const providers = config.config.llm.providers || {};
    return Object.entries(providers)
      .filter(([provider]) => {
        try {
          return config.validateLLMProvider(provider).valid;
        } catch {
          return false;
        }
      })
      .map(([provider]) => provider);
  }
  static validateEnvironment(type) {
    return config.validateLLMProvider(type).valid;
  }
}
