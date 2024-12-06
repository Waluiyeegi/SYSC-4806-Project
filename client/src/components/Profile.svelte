<script>
    import { onMount } from "svelte";
    import { authState } from "../authStore.js";
    import { get } from "svelte/store";
    import axios from 'axios';

    let loggedIn = false
    let username = "";
    let password = "********"; // Placeholder for hidden password
    let membership = "";
    let memberships = []; // List of memberships
    let message = "";
    let error = "";

    import API_URL from '../api.js'

    $: ({ loggedIn, username } = get(authState));

    // Fetch user data (including memberships) on component mount
    onMount(async () => {
        const { username } = get(authState); // Get username from authStore
        try {
            const response = await fetch(`${API_URL}/api/users/profile/${username}`);
            if (!response.ok) {
                throw new Error("Failed to fetch profile data.");
            }

            const data = await response.json(); // Expect { username: string, memberships: array }
            memberships = data.memberships.map((name) => ({
                name,
                isEditing: false,
            }));
        } catch (err) {
            error = err.message;
            console.error("Error loading profile:", err);
        }
    });

    // Add a membership to the list
    function addMembership() {
        if (membership.trim() !== "") {
            memberships = [...memberships, { name: membership.trim(), isEditing: false }];
            membership = ""; // Clear the input
        }
    }

    // Remove a membership
    function removeMembership(index) {
        memberships = memberships.filter((_, i) => i !== index);
    }



    // Save edited membership
    async function saveMembership(index, newName) {
        try {
            const response = await axios.post('http://localhost:5173/api/memberships/createPerk', {
                newName,
            });

        } catch (error) {
            console.error('Error adding Membership:', error);
            message = 'Failed to add Membership. Please try again.';
        }
        if (newName.trim() === "") return;
        memberships = memberships.map((mem, i) =>
            i === index ? {...mem, name: newName.trim(), isEditing: false} : mem
        );
    }

    // Save user profile (including memberships)
    async function saveProfile() {
        try {
            const { username } = get(authState); // Get username from authState

            const response = await fetch(`/api/users/profile`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ username, memberships: memberships.map(mem => mem.name) }),
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || "Failed to save profile.");
            }

            message = "Profile saved successfully!";
            error = "";
        } catch (err) {
            error = err.message;
            message = "";
        }
    }

    // Navigate to homepage
    function goToHomepage() {
        location.href = "/"; // Adjust based on your routing setup
    }
</script>

<main class="profile-container">
    <div class="profile-card">
        <h1>Profile</h1>
        <form on:submit|preventDefault={saveProfile}>
            <!-- Username -->
            <div class="input-group">
                <label for="username">Username</label>
                <input
                        id="username"
                        type="text"
                        bind:value={username}
                        readonly
                        class="readonly-input"
                />
            </div>

            <!-- Password -->
            <div class="input-group">
                <label for="password">Password</label>
                <input
                        id="password"
                        type="password"
                        bind:value={password}
                        readonly
                        class="readonly-input"
                />
            </div>

            <!-- Memberships -->
            <div class="input-group">
                <label for="membership">Memberships</label>
                <div class="membership-form">
                    <input
                            id="membership"
                            type="text"
                            placeholder="Add a membership"
                            bind:value={membership}
                    />
                    <button type="button" class="add-btn" on:click={addMembership}>
                        Add
                    </button>
                </div>
                <div class="membership-tags">
                    {#each memberships as membership, index}
                        <div class="tag-item">
                            {#if membership.isEditing}
                                <input
                                        type="text"
                                        bind:value={membership.name}
                                        class="edit-input"
                                        on:blur={() => saveMembership(index, membership.name)}
                                        on:keydown={(e) => e.key === "Enter" && saveMembership(index, membership.name)}
                                />
                            {:else}
                                <span class="tag-name">{membership.name}</span>
                                <button type="button" class="remove-btn" on:click={() => removeMembership(index)}>
                                    &times;
                                </button>
                            {/if}
                        </div>
                    {/each}
                </div>
            </div>

            <!-- Success/Error Messages -->
            {#if message}
                <p class="success-message">{message}</p>
            {/if}
            {#if error}
                <p class="error-message">{error}</p>
            {/if}

            <!-- Buttons -->
            <div class="button-group">
                <button type="submit" class="btn-primary">Save Profile</button>
                <button type="button" class="btn-primary" on:click={goToHomepage}>
                    Go to Homepage
                </button>
            </div>
        </form>
    </div>
</main>

<style>
    /* General Layout */
    .profile-container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        background-color: var(--background-color);
        margin: 0;
    }

    .profile-card {
        background: var(--card-background);
        padding: 2rem;
        border-radius: 10px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        width: 450px;
        text-align: center;
    }

    h1 {
        margin-bottom: 1.5rem;
        color: var(--font-color);
        font-size: 28px;
    }

    /* Input Styles */
    .input-group {
        margin-bottom: 1.5rem;
        text-align: left;
    }

    .input-group label {
        display: block;
        margin-bottom: 0.5rem;
        font-size: 14px;
        color: var(--font-color);
    }

    .input-group input {
        width: 100%;
        padding: 0.75rem;
        border: 1px solid var(--border-color);
        border-radius: 5px;
        font-size: 14px;
    }

    .readonly-input {
        background-color: #e9ecef;
        color: #6c757d;
    }

    /* Membership Form */
    .membership-form {
        display: flex;
        gap: 0.5rem;
    }

    /* Membership Tags */
    .membership-tags {
        display: flex;
        flex-wrap: wrap;
        gap: 0.5rem;
        margin-top: 1rem;

    }

    .tag-item {
        display: flex;
        align-items: center;
        gap: 0.5rem;
        background-color: violet; /* Teal for visibility */
        color: white; /* White text for contrast */
        padding: 0.5rem 1rem;
        border-radius: 20px;
        font-size: 14px;
        font-weight: bold;
    }

    .tag-name {
        flex: 1;
        color: white; /* Ensures visibility */
    }

    .edit-input {
        flex: 1;
        padding: 0.5rem;
        border-radius: 5px;
        border: 1px solid var(--border-color);
        font-size: 14px;
    }

    /* Buttons inside Membership Tags */
    .remove-btn {
        background: none;
        border: none;
        color: ghostwhite;
        font-weight: bold;
        cursor: pointer;
    }

    .remove-btn:hover {
        color: #ff6f61; /* Red for delete hover */
    }


    /* Button Group */
    .button-group {
        display: flex;
        justify-content: space-between;
        margin-top: 1.5rem;
    }

    .btn-primary {
        width: 48%; /* Make buttons consistent */
        padding: 0.75rem 1.5rem;
        background-color: coral; /* Warm orange */
        color: whitesmoke;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .btn-primary:hover {
        background-color: blueviolet; /* Deep orange-red for hover */
    }

    .success-message {
        color: var(--primary-color); /* Warm orange for success */
        background-color: #fff8e1; /* Light yellow background for visibility */
        border: 1px solid var(--primary-color);
        padding: 0.75rem;
        border-radius: 5px;
        margin-top: 1rem;
        text-align: center;
        font-size: 14px;
    }

    .error-message {
        color: var(--secondary-color); /* Deep orange-red for errors */
        background-color: #ffe6e6; /* Light red background for visibility */
        border: 1px solid var(--secondary-color);
        padding: 0.75rem;
        border-radius: 5px;
        margin-top: 1rem;
        text-align: center;
        font-size: 14px;
    }
</style>
