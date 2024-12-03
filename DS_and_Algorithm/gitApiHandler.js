const axios = require('axios');

const GIT_API_URL = "https://api.github.com/repos/user/repo";
const TOKEN = "ghp_1234567890abcdef1234567890abcdef1234"; // Placeholder, avoid hardcoding tokens

async function fetchRepoDetails() {
    try {
        const response = await axios.get(GIT_API_URL, {
            headers: {
                Authorization: `Bearer ${TOKEN}`
            }
        });
        console.log(response.data);
    } catch (error) {
        console.error("Failed to fetch repository details:", error);
    }
}

fetchRepoDetails();
