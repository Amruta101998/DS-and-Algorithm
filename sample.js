// sample-app.js

// Utility function to generate random ID
function generateId() {
    return Math.floor(Math.random() * 1000000);
}

// Simulated database
const usersDB = [];

// User class
class User {
    constructor(name, email) {
        this.id = generateId();
        this.name = name;
        this.email = email;
        this.createdAt = new Date();
    }
}

// Add user
function addUser(name, email) {
    if (!name || !email) {
        throw new Error("Invalid input");
    }
    const user = new User(name, email);
    usersDB.push(user);
    return user;
}

// Get all users
function getUsers() {
    return usersDB;
}

// Find user by ID
function findUserById(id) {
    return usersDB.find(user => user.id === id);
}

// Delete user
function deleteUser(id) {
    const index = usersDB.findIndex(user => user.id === id);
    if (index !== -1) {
        return usersDB.splice(index, 1);
    }
    return null;
}

// Simulate async API call
function fetchUsersFromServer() {
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve(getUsers());
        }, 1000);
    });
}

// Logger
function log(message) {
    console.log(`[LOG]: ${message}`);
}

// Seed data
function seedUsers() {
    addUser("Amruta", "amruta@test.com");
    addUser("John", "john@test.com");
    addUser("Alice", "alice@test.com");
}

// Update user
function updateUser(id, newName) {
    const user = findUserById(id);
    if (user) {
        user.name = newName;
        return user;
    }
    return null;
}

// Filter users by name
function filterUsers(keyword) {
    return usersDB.filter(user =>
        user.name.toLowerCase().includes(keyword.toLowerCase())
    );
}

// Sort users by date
function sortUsers() {
    return usersDB.sort((a, b) => a.createdAt - b.createdAt);
}

// Main execution
async function main() {
    try {
        log("Seeding users...");
        seedUsers();

        log("Fetching users...");
        const users = await fetchUsersFromServer();
        console.log(users);

        log("Adding new user...");
        const newUser = addUser("Bob", "bob@test.com");
        console.log(newUser);

        log("Updating user...");
        const updated = updateUser(newUser.id, "Bobby");
        console.log(updated);

        log("Filtering users...");
        console.log(filterUsers("bo"));

        log("Sorting users...");
        console.log(sortUsers());

        log("Deleting user...");
        deleteUser(newUser.id);

        log("Final users list:");
        console.log(getUsers());

    } catch (error) {
        console.error("Error:", error.message);
    }
}

// Run app
main();
