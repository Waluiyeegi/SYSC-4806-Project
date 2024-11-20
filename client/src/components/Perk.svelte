<script>
    import { onMount } from 'svelte';

    export let buttonText = 'View Perk';

    let text = 'Perk Loading...';

    onMount(async () => {
        const response = await fetch('/api/dynamic-text');
        const data = await response.text();
        text = data;
    });
</script>

<div class="fancy-box">
    <div class="text-container">
        <p>{text}</p>
    </div>
    <button class="fancy-button">{buttonText}</button>
</div>

<style>
    .fancy-box {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 20px;
        border: 2px solid #333;
        border-radius: 10px;
        background-color: #f0f0f0;
        box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.2);
    }

    .text-container {
        flex-grow: 1;
        font-size: 18px;
        font-weight: bold;
        color: #333;
    }

    .fancy-button {
        padding: 15px 25px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 10px;
        cursor: pointer;
        transition: background-color 0.3s
        ease-in-out;
    }

    .fancy-button:hover {
        background-color: #0056b3;
    }
</style>
