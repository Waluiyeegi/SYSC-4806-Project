import Home from './components/Home.svelte';
import Login from './components/Login.svelte';
import Register from './components/Register.svelte';
import Profile from './components/Profile.svelte';
import Dashboard from './components/Dashboard.svelte';

export const routes = {
    '/': Home,
    '/login': Login,
    '/register': Register,
    '/profile': Profile,
    '/dashboard': Dashboard,
};
