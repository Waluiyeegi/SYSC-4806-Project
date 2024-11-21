<script>
    let promise = getPerk();

    async function getPerk() {
        try {
            const res = await fetch(`/perk`); // Ensure the correct URL
            const perk = await res.json();

            if (!res.ok) {
                throw new Error(perk.message || "Failed to fetch perk data");
            }
            return perk;
        } catch (error) {
            throw new Error("An error occurred while fetching the perk data");
        }
    }

    function goBack() {
        history.back(); // Navigates to the previous page in the browser's history
    }
</script>

{#await promise}
    <p>...waiting</p>
{:then perk}
    <div class="perk-card card border shadow-sm">
        <div class="card-body">
            <h5 class="card-title">{perk.name || "Unknown Perk"}</h5>
            <div class="d-flex justify-content-between align-items-center">
                <p class="mb-0">Upvotes: {perk.upvote ?? 0}</p>
                <p class="mb-0">Downvotes: {perk.downvote ?? 0}</p>
            </div>
            <p class="card-text">Restrictions: {perk.restrictions || "None"}</p>
            <p class="card-text">
                Total Votes: {(perk.upvote ?? 0) + (perk.downvote ?? 0)}
            </p>
            <button class="btn btn-secondary mt-3" on:click={goBack}>Go Back</button>
        </div>
    </div>
{:catch error}
    <p style="color: red">{error.message}</p>
    <button class="btn btn-secondary mt-3" on:click={goBack}>Go Back</button>
{/await}

<style>
    .perk-card {
        border-radius: 10px; /* Example for rounded corners */
    }
    .btn {
        padding: 10px 20px;
        border-radius: 5px;
    }
</style>
