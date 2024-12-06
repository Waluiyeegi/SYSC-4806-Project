<script>
    import { fade } from 'svelte/transition';  // Import fade transition
    import { cubicOut } from 'svelte/easing';  // Import easing for optional custom easing
    import { createEventDispatcher } from 'svelte';
    // Props
    export let perk;
    export let buttonText = 'View Perk';
    export let deleteButton = 'Delete Perk';
    const dispatch = createEventDispatcher(); // Initialize event dispatcher

    let showBack = false;  // Controls when to show the back of the card
    let voteType = null;   // Tracks the current vote type: 'upvote', 'downvote', or null

    const deletePerk = async () => {
        try{
            const response = await fetch(`/api/perks/${perk.id}/deleteNewPerk`, { method: 'DELETE'});
            if(response.ok){
                console.log("Perk deleted successfully");
                dispatch('delete', perk.id); //
            }
            else {
                console.log("Perk not deleted successfully");
            }
        }catch (error){
            console.error("Error deleting perk", error);
        }
    }

    const toggleFlip = () => {
        showBack = !showBack; // Toggle front/back content
    };

    const updateVote = async (newVoteType) => {
        try {
            let response;

            // If the user is changing their vote
            if (voteType === 'upvote' && newVoteType === 'downvote') {
                // Decrease upvote and increase downvote
                response = await fetch(`/api/perks/${perk.id}/downvote`, { method: 'POST' });
                perk.upvotes -= 1;
                perk.downvotes += 1;
            } else if (voteType === 'downvote' && newVoteType === 'upvote') {
                // Decrease downvote and increase upvote
                response = await fetch(`/api/perks/${perk.id}/upvote`, { method: 'POST' });
                perk.downvotes -= 1;
                perk.upvotes += 1;
            } else if (voteType === null) {
                // If the user is voting for the first time
                if (newVoteType === 'upvote') {
                    response = await fetch(`/api/perks/${perk.id}/upvote`, { method: 'POST' });
                    perk.upvotes += 1;
                } else if (newVoteType === 'downvote') {
                    response = await fetch(`/api/perks/${perk.id}/downvote`, { method: 'POST' });
                    perk.downvotes += 1;
                }
            }

            if (response.ok) {
                voteType = newVoteType; // Update the current vote type
            } else {
                console.error('Failed to update vote');
            }
        } catch (error) {
            console.error('Error updating vote:', error);
        }
    };
</script>

<div class="perk-container">
    <div class="perk-card">
        <!-- Front of the card -->
        {#if !showBack}
            <div class="card-front" in:fade={{ duration: 300 }} out:fade={{ duration: 300 }}>
                <h2>{perk.name}</h2>
                <div class="votes">
                    <div class="vote-option">
                        <button
                                class="arrow-button"
                                on:click|stopPropagation={() => updateVote('upvote')}
                                class:active={voteType === 'upvote'}
                        >
                            ⬆️
                        </button>
                        <span class="vote-count upvote">{perk.upvotes}</span>
                    </div>
                    <div class="vote-option">
                        <button
                                class="arrow-button"
                                on:click|stopPropagation={() => updateVote('downvote')}
                                class:active={voteType === 'downvote'}
                        >
                            ⬇️
                        </button>
                        <span class="vote-count downvote">{perk.downvotes}</span>
                    </div>
                </div>
                <p class="description-text">Location: {perk.geographicArea}</p>
                <p class="description-text">Expiry: {perk.expiryDate}</p>
                <button class="flip-button" on:click={toggleFlip}>{buttonText}</button>
                <button class="delete-button" on:click={deletePerk}>{deleteButton}</button>
            </div>
        {/if}

        <!-- Back of the card -->
        {#if showBack}
            <div class="card-back" in:fade={{ duration: 300 }} out:fade={{ duration: 300 }}>
                <h2>Perk Code</h2>
                <p>{perk.code}</p>
                <button class="flip-button" on:click={toggleFlip}>Go Back</button>
            </div>
        {/if}
    </div>
</div>

<style>
    /* Centering the card in the viewport */
    .perk-container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    /* Card container */
    .perk-card {
        position: relative;
        width: 300px;
        height: 400px;
        border-radius: 15px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        overflow: hidden;
        background-color: white;
    }

    .card-front,
    .card-back {
        position: absolute;
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        backface-visibility: hidden; /* Hide the back during the transition */
        border-radius: 15px;
        padding: 20px;
        box-sizing: border-box;
    }

    .card-back {
        background: linear-gradient(145deg, #ff9a8b, #ff6a88); /* Backside gradient */
        color: white;
    }

    h2 {
        font-size: 24px;
        margin-bottom: 15px;
    }

    .votes {
        display: flex;
        justify-content: space-around;
        width: 100%;
    }

    .vote-option {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .arrow-button {
        font-size: 24px;
        background: none;
        border: none;
        cursor: pointer;
        transition: transform 0.2s ease-in-out;
    }

    .arrow-button:hover:not(:disabled) {
        transform: scale(1.2);
    }

    .arrow-button:disabled {
        opacity: 0.5;
    }

    .vote-count {
        font-weight: bold;
    }

    .upvote {
        color: #2ecc71;
    }

    .downvote {
        color: #e74c3c;
    }

    .description-text {
        font-size: 14px;
        color: #555;
    }

    .flip-button {
        padding: 10px 20px;
        background-color: #3498db;
        color: white;
        border: none;
        border-radius: 25px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .flip-button:hover {
        background-color: #2980b9;
    }
</style>
