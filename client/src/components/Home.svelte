<script>
  import { onMount } from 'svelte';
  import Perk from './Perk.svelte'; // Import the Perk component

  let loggedIn = false;
  let perks = [];

  async function fetchPerks() {
    try {
      const response = await fetch('/api/perks');
      if (!response.ok) {
        throw new Error('Failed to fetch perks');
      }
      perks = await response.json();
    } catch (error) {
      console.error('Error fetching perks:', error);
      perks = [];
    }
  }

  onMount(fetchPerks);
</script>

<main>
  <div class="home-page">
    <div class="top-bar">
      <div class="top-bar-left"></div>
      <div class="top-bar-right">
        {#if loggedIn}
          <a href="/profile"><button>Profile</button></a>
          <button on:click={() => { loggedIn = false; }}>Log Out</button>
        {:else}
          <a href="/login"><button>Log In</button></a>
          <a href="/register"><button>Register</button></a>
        {/if}
      </div>
    </div>
    <div class="content-section">
      <div class="filter-section">
        <h3>Filter Section</h3>
      </div>
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
  /* Your existing styles remain unchanged */
</style>
