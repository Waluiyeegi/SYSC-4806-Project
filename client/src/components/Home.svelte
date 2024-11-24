<script>
  import { authState } from "../authStore";
  import { Link } from "svelte-routing";
  import API_URL from '../api.js';


  let perks = [];

  // Subscribe to authState
  $: loggedIn = $authState.loggedIn;

  async function fetchPerks() {
      // Fetch perks from the backend (replace with actual API if needed)
      const response = await fetch(`/api/perks`);
      perks = await response.json();
  }

  fetchPerks();

  function logOut() {
      authState.set({
          loggedIn: false,
          username: null,
      });
  }

</script>

<main>
  <div class="home-page">
    <div class="top-bar">
      <div class="top-bar-left">
        <!-- for later use, if we add a logo or need more buttons -->
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
          <button class="add-perk-btn">Add Perk</button>
          <div class="perk-list">
            <ul>
              {#each perks as perk (perk.id)}
                <li class="perk-item">
                  <Perk {perk} />
                </li>
              {/each}
            </ul>
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
    background-color: #f0f0f0;
  }

  .home-page {
		display: flex;
	  flex-direction: column;
    height: 100vh;
    width: 100vw;
    overflow: hidden;
  }

  .top-bar {
      height: 60px; /* Define the exact height */
      display: grid;
      grid-template-columns: 1fr auto;
      justify-content: space-between;
      position: fixed;
      top: 0;
      left: 0;
      right: 0;
      background-color: #333;
      color: white;
      padding: 1rem;
      z-index: 100; /* Ensure it's on top of other elements */
  }



  .top-bar-left svg {
      margin-top: -80px; /* Adjust value as needed */
  }

  .top-bar-right button {
    background-color: #2a9d8f;
    color: white;
    padding: 0.5rem 1rem;
    cursor: pointer;
  }

  .top-bar-right button:hover {
    background-color: #264653;
  }

  .content-section {
      height: calc(100vh - 60px); /* Adjust height dynamically */
      margin-top: 60px; /* Push content below the top bar */
      width: 100vw;
      display: flex;
      flex-direction: row;
      justify-content: space-between;
  }

  .filter-section {
    background-color: #2a9d8f;
    padding: 2rem;
    color: white;
		width: 25%;
  }

  .perk-list-section {
    background-color: #f4a261;
    padding: 2rem;
    overflow-y: auto;
    height: calc(100vh - 120px);
		flex-grow: 1;
  }

  .perk-list {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    gap: 1rem;
  }

  .perk-item {
    padding: 0.5rem;
    background-color: #e76f51;
    color: white;
  }

  .add-perk-btn {
    background-color: #e76f51;
    color: white;
    border: none;
    padding: 0.5rem 1rem;
    cursor: pointer;
    border-radius: 5px;
  }

  .add-perk-btn:hover {
    background-color: #f4a261;
  }
</style>