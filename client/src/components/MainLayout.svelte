<script>
    import Sidebar from './Sidebar.svelte';
    import { Router, Route } from 'svelte-routing';
    import Home from './Home.svelte';
    import Login from './Login.svelte';
    import Register from './Register.svelte';
    import Profile from './Profile.svelte';

    let isLoggedIn = true; // Simulated login state

    function handleLogin() {
        isLoggedIn = true;
    }

    function handleLogout() {
        isLoggedIn = false;
    }
</script>

<div class="main-layout">
    <Sidebar {isLoggedIn} />

    <main class="router-container">
        <Router>
            <Route path="/" component={Home} />
            <Route path="/login" component={Login} />
            <Route path="/register" component={Register} />
            <Route path="/profile" component={Profile} />
        </Router>
    </main>
</div>

<style>
    .main-layout {
        display: flex;
        flex-direction: row;
        height: 100vh;
    }

    .router-container {
        flex-grow: 1;
        padding: 1rem;
        overflow-y: auto;
    }
</style>
