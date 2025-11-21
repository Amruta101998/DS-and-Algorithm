const axios = require('axios');

const GIT_API_URL = process.env.GITHUB_API_URL || "https://api.github.com/repos/user/repo";
const TOKEN = process.env.GITHUB_TOKEN || ""; // Load from environment variables

async function fetchRepoDetails() {
    try {
        if (!TOKEN || typeof TOKEN !== 'string' || !TOKEN.trim()) {
            throw new Error('GitHub token is required and must be a non-empty string');
        }
        if (!TOKEN.match(/^(gh[ps]_[a-zA-Z0-9]{36}|[a-zA-Z0-9]{40})$/)) {
            throw new Error('GitHub token format appears invalid');
        // Add request timeout and retry logic
        const maxRetries = 3;
        let retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                const response = await axios.get(GIT_API_URL, {
                    headers: {
                        Authorization: `Bearer ${TOKEN}`,
                        'Accept': 'application/vnd.github.v3+json'
                    },
                    timeout: 5000, // 5 seconds timeout
                    validateStatus: function (status) {
                        return status >= 200 && status < 300;
                    }
});

                // Validate response data
                if (!response.data) {
                    throw new Error('No data received from API');
                }
                return response.data;
            } catch (error) {
                retryCount++;
                if (error.response) {
                    switch (error.response.status) {
                        case 401:
                            throw new Error('Authentication failed. Please verify your GitHub token and permissions');
                        case 403:
                            throw new Error('Access forbidden. Please check repository access permissions');
                        case 429:
                        throw new Error('Rate limit exceeded');
                        default:
                            throw new Error(`API error: ${error.response.status}. Request ID: ${error.response.headers['x-request-id'] || 'unknown'}`);
                        }
                } else if (error.request) {
                    // Handle network errors
                    if (retryCount < maxRetries) {
                        await new Promise(resolve => setTimeout(resolve, 1000 * retryCount));
                        continue;
                    }
                    throw new Error('Network error: Unable to reach GitHub API');
                } else {
                    // Handle other errors
                    throw new Error(`Request failed: ${error.message}`);
                }
            }
        }
        throw new Error('Max retries exceeded');
    } catch (error) {
        console.error("Failed to fetch repository details:", error.message);
        throw error; // Re-throw for handling by caller
    }
}

fetchRepoDetails();
