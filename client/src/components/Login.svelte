<script>
    import { writable } from "svelte/store";
    import { authState } from "../authStore";
    import { navigate } from "svelte-routing"; // Use navigate for redirection
    import axios from "axios";
    import API_URL from '../api.js';

    let username = "";
    let password = "";
    let error = "";

    async function handleLogin() {
        try {
            const response = await axios.post(`/api/users/login`, {
                username,
                password,
            });

            error = "";

            // Update the authentication state
            authState.set({
                loggedIn: true,
                username: response.data.username,
            });

            // Redirect to the homepage
            navigate("/");
        } catch (err) {
            error = err.response?.data || "Invalid username or password.";
        }
    }
</script>

<main class="auth-container">
    <div class="auth-card">
        <h1>Welcome Back</h1>
        <p class="subtitle">Log in to continue</p>
        <form on:submit|preventDefault={handleLogin}>
            <div class="input-group">
                <label for="username">Username</label>
                <input
                        id="username"
                        type="text"
                        placeholder="Enter your username"
                        bind:value={username}
                        required
                />
            </div>
            <div class="input-group">
                <label for="password">Password</label>
                <input
                        id="password"
                        type="password"
                        placeholder="Enter your password"
                        bind:value={password}
                        required
                />
            </div>
            {#if error}
                <p class="error-message">{error}</p>
            {/if}
            <button type="submit" class="btn-primary">Log In</button>
        </form>
        <p class="link-text">
            Don’t have an account? <a href="/register">Register</a>
        </p>
    </div>
</main>

<style>
    /* Container and layout */
    .auth-container {
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #f0f4f8;
        margin: 0;
    }
    .auth-card {
        background: white;
        padding: 2rem;
        border-radius: 8px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        text-align: center;
        width: 400px;
    }

    /* Titles */
    h1 {
        margin: 0 0 1rem;
        color: #333;
        font-size: 24px;
    }
    .subtitle {
        margin: 0 0 1.5rem;
        color: #666;
        font-size: 14px;
    }

    /* Form elements */
    .input-group {
        margin-bottom: 1rem;
        text-align: left;
    }
    .input-group label {
        display: block;
        margin-bottom: 0.5rem;
        font-size: 14px;
        color: #555;
    }
    .input-group input {
        width: 100%;
        padding: 0.75rem;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 14px;
    }

    /* Buttons */
    .btn-primary {
        width: 100%;
        padding: 0.75rem;
        background-color: #2a9d8f;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 16px;
    }
    .btn-primary:hover {
        background-color: #21867a;
    }

    /* Error and links */
    .error-message {
        color: red;
        font-size: 14px;
        margin-bottom: 1rem;
    }
    .link-text {
        margin-top: 1.5rem;
        font-size: 14px;
    }
    .link-text a {
        color: #2a9d8f;
        text-decoration: none;
    }
    .link-text a:hover {
        text-decoration: underline;
    }
</style>

