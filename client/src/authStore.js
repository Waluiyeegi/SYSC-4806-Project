import { writable } from "svelte/store";

const storedAuthState = JSON.parse(localStorage.getItem("authState")) || {
    loggedIn: false,
    username: "",
};

export const authState = writable(storedAuthState);

authState.subscribe((value) => {
    localStorage.setItem("authState", JSON.stringify(value));
});
