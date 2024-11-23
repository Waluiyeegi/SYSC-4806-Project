// src/authStore.js
import { writable } from "svelte/store";

export const authState = writable({
    loggedIn: false,
    username: null,
});
