import Home from './components/Home.svelte';
import Login from './components/Login.svelte';
import Register from './components/Register.svelte';
import Profile from './components/Profile.svelte';

export const routes = {
    '/': Home,        // Home page
    '/login': Login,  // Login page
    '/register': Register, // Register page
    '/profile': Profile,   // Profile page
};
