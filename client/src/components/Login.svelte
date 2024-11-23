<script>
  export let onLogin = () => {}; // Callback to update the login state in App.svelte

  let username = '';
  let password = '';

  function handleLogin() {
    if (username && password) {
      onLogin(); // Notify the parent component about the login
      window.location.href = '/'; // Redirect to Home page
    } else {
      alert('Please enter a username and password.');
    }
  }
</script>

<h1>Login</h1>
<form on:submit|preventDefault={handleLogin}>
  <label for="username">Username:</label>
  <input id="username" type="text" bind:value={username} />

  <label for="password">Password:</label>
  <input id="password" type="password" bind:value={password} />

  <button type="submit">Login</button>
</form>
