<script>
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

        if (password.length < 8) {
            error = "Password must be at least 8 characters long.";
            return;
        }

        try {
            const response = await fetch("/api/users/register", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ username, password }),
            });

            if (!response.ok) {
                const errorData = await response.json().catch(() => ({ message: "Unexpected error occurred." }));
                throw new Error(errorData.message || "Registration failed.");
            }

            const user = await response.json(); // Contains { username }
            message = `Registration successful for ${user.username}. Redirecting to profile creation...`;
            error = "";

            setTimeout(() => {
                window.location.href = "/profile-creation"; // Navigate to profile creation
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
    /* Styling remains the same as your original code */
</style>
