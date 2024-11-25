<script>
    import API_URL from '../api.js';
    import { authState } from "../authStore";
    import { get } from "svelte/store";

    let username = "";
    let password = "";
    let confirmPassword = "";
    let error = "";
    let message = "";


    async function handleRegister() {

        if (password !== confirmPassword) {
            error = "Passwords do not match.";
            return;
        }
        try {
            const response = await fetch(`${API_URL}/api/users/register`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ username, password }),
            });

            if (!response.ok) {
                const errorData = await response.text();
                throw new Error(errorData.message || "Registration failed.");
            }

            const user = await response.json(); // Contains { username }

            // Update the userStore
            authState.set({
                loggedIn: true,
                username: user.username,
            });

            message = `Registration successful for ${user.username}. Redirecting to profile creation...`;
            error = "";

            setTimeout(() => {
                location.href = "/profile"; // Navigate to profile page
            }, 2000);
        } catch (err) {
            error = err.message;
            message = "";
        }
    }
</script>

<main class="auth-container">
    <div class="auth-card">
        <h1>Create an Account</h1>
        <p class="subtitle">Register to get started</p>
        <form on:submit|preventDefault={handleRegister}>
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
            <div class="input-group">
                <label for="confirmPassword">Confirm Password</label>
                <input
                        id="confirmPassword"
                        type="password"
                        placeholder="Re-enter your password"
                        bind:value={confirmPassword}
                        required
                />
            </div>
            {#if error}
                <p class="error-message">{error}</p>
            {/if}
            {#if message}
                <p class="success-message">{message}</p>
            {/if}
            <button type="submit" class="btn-primary">Register</button>
        </form>
        <p class="link-text">
            Already have an account? <a href="/login">Log in</a>
        </p>
    </div>
</main>

<style>
    /* Styling remains the same */
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

    .error-message {
        color: red;
        font-size: 14px;
        margin-bottom: 1rem;
    }

    .success-message {
        color: green;
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
