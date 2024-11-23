<script>
  import Router from 'svelte-spa-router';
  import { routes } from './routes.js';

  let isLoggedIn = true;

  function handleLogin() {
    isLoggedIn = true;
  }

  function handleLogout() {
    isLoggedIn = false;
  }
</script>

<div class="app-container">
  <nav class="top-bar">
    <div class="top-bar-left">
      <a href="/">Home</a>
      <a href="/login">Login</a>
      <a href="/register">Register</a>
    </div>
    <div class="top-bar-right">
      {#if isLoggedIn}
        <button on:click={handleLogout}>Logout</button>
      {/if}
    </div>
  </nav>

  <div class="main-content">
    {#if isLoggedIn}
    <aside class="sidebar">
      <h2>Sidebar</h2>
      <ul>
        <li><a href="/profile">Profile</a></li>
        <li><a href="/dashboard">Dashboard</a></li>
      </ul>
    </aside>
    {/if}

    <main class="router-container">
      <Router {routes} let:component>
        {#if component}
          <svelte:component this={component} />
        {:else}
          <p>No matching route found for the current URL.</p>
        {/if}
      </Router>
    </main>
  </div>
</div>

<style>
  .app-container {
    display: flex;
    flex-direction: column;
    height: 100vh;
  }

  .top-bar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 1rem;
    background-color: #333;
    color: white;
  }

  .top-bar a {
    color: white;
    margin-right: 1rem;
    text-decoration: none;
  }

  .top-bar button {
    background-color: #2a9d8f;
    color: white;
    border: none;
    padding: 0.5rem 1rem;
    cursor: pointer;
  }

  .top-bar button:hover {
    background-color: #21867a;
  }

  .main-content {
    display: flex;
    flex: 1;
    overflow: hidden;
  }

  .sidebar {
    background-color: #f4f4f4;
    width: 200px;
    padding: 1rem;
    box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
  }

  .sidebar ul {
    list-style: none;
    padding: 0;
  }

  .sidebar ul li {
    margin-bottom: 1rem;
  }

  .router-container {
    flex-grow: 1;
    padding: 1rem;
    overflow-y: auto;
    background-color: #f0f0f0;
  }
</style>
