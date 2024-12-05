<script>


    let product = '';
    let membership = ''; // Holds the selected membership ID
    let memberships = []; // Holds the list of memberships fetched from the backend
    let description = '';
    let geographicArea = '';
    let expiryDate = '';
    let message = '';
    let errors = {}; // Object to store field-specific error messages

    // Fetch memberships when the component loads
    async function fetchMemberships() {
        try {
            const response = await axios.get('http://localhost:8080/api/memberships'); // Adjust the URL if necessary
            memberships = response.data; // Store memberships from the response
        } catch (error) {
            console.error('Error fetching memberships:', error);
            message = 'Failed to load memberships.';
        }
    }

    // Validate form fields
    function validateFields() {
        errors = {};
        if (!product) errors.product = 'Product name is required.';
        if (!membership) errors.membership = 'Membership is required.';
        if (!description) errors.description = 'Description is required.';
        return Object.keys(errors).length === 0; // Validation passes if no errors
    }

    // Submit form
    async function submitForm() {
        if (!validateFields()) {
            message = 'Please fix the errors and try again.';
            return;
        }

        try {
            const response = await axios.post('http://localhost:8080/api/perks', {
                product,
                membership,
                description,
                geographicArea,
                expiryDate,
            });

            if (response.status === 201) {
                message = 'Perk added successfully!';
                // Clear form fields
                product = '';
                membership = '';
                description = '';
                geographicArea = '';
                expiryDate = '';
                errors = {};
            }
        } catch (error) {
            console.error('Error adding perk:', error);
            message = 'Failed to add perk. Please try again.';
        }
    }

    // Fetch memberships on component load
    fetchMemberships();
</script>

<main>
    <nav>
        <a href="/">Home</a>
    </nav>

    <h1>Add a New Perk</h1>
    <form on:submit|preventDefault={submitForm}>
        <div>
            <input
                    type="text"
                    placeholder="Product"
                    bind:value={product}
                    required
            />
            {#if errors.product}
                <p class="error">{errors.product}</p>
            {/if}
        </div>

        <div>
            <!-- Membership Dropdown -->
            <select bind:value={membership} required>
                <option value="" disabled selected>Select Membership</option>
                {#each memberships as membershipItem}
                    <option value={membershipItem.id}>{membershipItem.name}</option>
                {/each}
            </select>
            {#if errors.membership}
                <p class="error">{errors.membership}</p>
            {/if}
        </div>

        <div>
      <textarea
              placeholder="Description"
              bind:value={description}
              required
      ></textarea>
            {#if errors.description}
                <p class="error">{errors.description}</p>
            {/if}
        </div>

        <div>
            <input
                    type="text"
                    placeholder="Geographic Area"
                    bind:value={geographicArea}
            />
        </div>

        <div>
            <input
                    type="date"
                    placeholder="Expiry Date"
                    bind:value={expiryDate}
            />
        </div>

        <button type="submit">Add Perk</button>
    </form>

    <p class="message">{message}</p>
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

    input, textarea, button {
        width: 100%;
        padding: 0.5rem;
        font-size: 1rem;
    }

    select {
        width: 103%;
        padding: 0.5rem;
        font-size: 1rem;
    }

    button {
        background-color: #2a9d8f;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    button:hover {
        background-color: #21867a;
    }
</style>
