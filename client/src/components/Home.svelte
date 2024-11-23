<script>
  import { onMount } from "svelte";
  import { Link } from "svelte-routing";


  let loggedIn = false;
  let perks = [];

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
                <button on:click={() => { loggedIn = false; }}>Log Out</button>
            {:else}
                <Link to="/login"><button>Log In</button></Link>
                <Link to="/register"><button>Register</button></Link>
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
    grid-column: 1 / span 2;
    background-color: #333;
    color: white;
    padding: 1rem;
    display: flex;
    justify-content: space-between;
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

  .content-section{
    height: 100vh;
    width: 100vw;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    height: calc(100vh - 60px);
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