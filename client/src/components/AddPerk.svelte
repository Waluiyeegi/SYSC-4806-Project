<script>
    import { onMount } from 'svelte';
    import { goto } from '$app/navigation';

    // Form data for the new perk
    let perk = {
        product: '',
        membership: '',
        description: '',
        geographicArea: '',
        expiryDate: '',
    };

    // Submit the form to the backend
    async function addPerk() {
        try {
            const response = await fetch('http://localhost:8080/api/perks', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(perk),
            });

            if (response.ok) {
                alert('Perk added successfully!');
                goto('/'); // Navigate to another page after submission
            } else {
                const error = await response.json();
                alert('Error: ' + error.message);
            }
        } catch (error) {
            console.error('Failed to add perk:', error);
            alert('An unexpected error occurred.');
        }
    }
</script>

<main>
    <h1>Add a New Perk</h1>
    <form on:submit|preventDefault={addPerk}>
        <div>
            <label for="product">Product</label>
            <input type="text" id="product" bind:value={perk.product} required />
        </div>
        <div>
            <label for="membership">Membership</label>
            <input type="text" id="membership" bind:value={perk.membership} required />
        </div>
        <div>
            <label for="description">Description</label>
            <textarea id="description" bind:value={perk.description} required></textarea>
        </div>
        <div>
            <label for="geographicArea">Geographic Area</label>
            <input type="text" id="geographicArea" bind:value={perk.geographicArea} required />
        </div>
        <div>
            <label for="expiryDate">Expiry Date</label>
            <input type="date" id="expiryDate" bind:value={perk.expiryDate} required />
        </div>
        <button type="submit">Add Perk</button>
    </form>
</main>

<style>
    main {
        max-width: 600px;
        margin: 2rem auto;
        font-family: Arial, sans-serif;
    }

    form div {
        margin-bottom: 1rem;
    }

    label {
        display: block;
        font-weight: bold;
        margin-bottom: 0.5rem;
    }

    input, textarea, button {
        width: 100%;
        padding: 0.5rem;
        font-size: 1rem;
    }

    button {
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    button:hover {
        background-color: #0056b3;
    }
</style>
