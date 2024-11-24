<script>
    export let isLoggedIn = false;
</script>

{#if isLoggedIn}
    <aside class="sidebar">
        <h2>Sidebar</h2>
        <ul>
            <li><a href="/profile">Profile</a></li>
            <li><a href="/dashboard">Dashboard</a></li>
        </ul>
    </aside>
{/if}

<style>
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
</style>
