<script>
  import { onMount } from "svelte";

  let perks = [];

  onMount(async () => {
    const response = await fetch("http://localhost:8080/api/perks");
    perks = await response.json();
  });

</script>

<style>

  main {
    margin: 0;
    padding: 0;
    height: 100%;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #f0f0f0;
  }

  .home-page {
    display: grid;
    grid-template-columns: 25% 1fr;
    grid-template-rows: auto 1fr;
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
    align-items: center;
  }

  .top-bar-left button,
  .top-bar-right button {
    background-color: #2a9d8f;
    color: white;
    padding: 0.5rem 1rem;
    cursor: pointer;
  }

  .top-bar-left button:hover,
  .top-bar-right button:hover {
    background-color: #264653;
  }

  .filter-section {
    background-color: #2a9d8f;
    padding: 2rem;
    color: white;
  }

  .perk-list-section {
    background-color: #f4a261;
    padding: 2rem;
    overflow-y: auto;
    height: calc(100vh - 120px);
  }

  .perk-list {
    margin-bottom: 1rem;
  }

  .perk-item {
    padding: 0.5rem;
    background-color: #e76f51;
    color: white;
    margin-bottom: 0.5rem;
    border-radius: 5px;
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

<main>
  <div class="home-page">
    <div class="top-bar">
      <div class="top-bar-left">
        <button>Profile</button>
      </div>
      <div class="top-bar-right">
        <button>Log In</button>
        <button>Register</button>
      </div>
    </div>

    <div class="filter-section">
      <h3>Filter Section</h3>
    </div>

    <div class="perk-list-section">
      <div class="perk-list">
        <ul>
          {#each perks as perk (perk.id)}
            <li class="perk-item">
              <Perk {perk} />
            </li>
          {/each}
        </ul>
      </div>
      <button class="add-perk-btn">Add Perk</button>
    </div>
  </div>
</main>