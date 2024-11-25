<script>
  import { authState } from "../authStore";
  import { Link } from "svelte-routing";
  import { get } from "svelte/store";
  import { onMount } from 'svelte';
  import Perk from './Perk.svelte'; // Import the Perk component
  import API_URL from '../api.js'

  let perks = [];

  // Subscribe to authState
  let { loggedIn, username } = get(authState);

  $: ({ loggedIn, username } = get(authState));

  async function fetchPerks() {
      // Fetch perks from the backend (replace with actual API if needed)
      const response = await fetch(`${API_URL}/api/perks`);
      perks = await response.json();
  }

  onMount(fetchPerks);


  function logOut() {
      authState.set({
          loggedIn: false,
          username: "",
      });
      location.href = "/"; // Redirect to the homepage
  }

</script>

<main>
    <div class="home-page">
        <div class="top-bar">
            <div class="top-bar-left">
                <h1 class="title">Perk Manager</h1>
            </div>
            <div class="top-bar-right">
                {#if loggedIn}
                    <Link to="/profile"><button>Profile</button></Link>
                    <button on:click={logOut}>Log Out</button>
                {:else}
                    <Link to="/login"><button>Log In</button></Link>
                    <Link to="/register"><button>Register</button></Link>
                {/if}
            </div>
        </div>
        <div class="content-section">
            {#if loggedIn}
                <div class="filter-section">
                    <h3>Filter Section</h3>
                    <!-- Add filter UI here -->
                </div>
            {/if}
            <div class="perk-list-section">
                <Link to="/addNewPerk"><button class="add-perk-btn">Add Perk</button></Link>
                <div class="perk-list">
                    {#each perks as perk (perk.id)}
                        <Perk {perk} />
                    {/each}
                </div>
            </div>
        </div>
    </div>
</main>

<style>
    main {
        height: 100%;
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #f9f9f9;
    }

    .home-page {
        display: flex;
        flex-direction: column;
        height: 100vh;
        width: 100vw;
        overflow: hidden;
    }

    .title {
        font-size: 28px; /* Large font for the title */
        font-weight: bold;
        color: #ffffff;
        margin: 0;
        padding: 0;
    }

    .top-bar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        background-color: #333;
        color: white;
        padding: 1rem;
        z-index: 100;
    }

    .top-bar-right button {
        background-color: #2a9d8f;
        color: white;
        padding: 0.5rem 1rem;
        cursor: pointer;
        border: none;
        border-radius: 5px;
        transition: background-color 0.3s;
    }

    .top-bar-right button:hover {
        background-color: #264653;
    }

    .content-section {
        padding-top: 60px;
        display: flex; /* Ensure horizontal alignment */
        flex-grow: 1;
        width: 100%;
        height: calc(100vh - 60px);
    }

    .filter-section {
        background-color: #2a9d8f;
        padding: 2rem;
        color: white;
        width: 25%; /* Take up 25% of the space */
        max-width: 300px;
        box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
    }

    .perk-list-section {
        background-color: #ffffff;
        padding: 2rem;
        flex-grow: 1; /* Take up remaining space */
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        display: flex;
        flex-direction: column;
        align-items: center;
        overflow-y: auto;
    }

    .perk-list {
        display: grid;
        grid-template-columns: repeat(4, 1fr);
        gap: 1.5rem;
        width: 100%;
    }

    .add-perk-btn {
        background-color: #e76f51;
        color: white;
        border: none;
        padding: 0.8rem 1.5rem;
        cursor: pointer;
        border-radius: 5px;
        font-size: 16px;
        font-weight: bold;
        margin-bottom: 1.5rem;
        transition: background-color 0.3s;
    }

    .add-perk-btn:hover {
        background-color: #f4a261;
    }

    @media (max-width: 768px) {
        .content-section {
            flex-direction: column;
        }

        .filter-section {
            width: 100%;
            max-width: none;
            box-shadow: none;
        }

        .perk-list {
            grid-template-columns: 1fr 1fr;
        }
    }

    @media (max-width: 480px) {
        .perk-list {
            grid-template-columns: 1fr;
        }
    }
</style>
