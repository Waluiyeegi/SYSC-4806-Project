<script>
    // Arbitrary values for testing
    export let perk;

    export let buttonText = 'View Perk';

    let flipped = false;
    let hasVoted = false;

    const toggleFlip = () => {
        flipped = !flipped;
    };

    const incrementUpvotes = () => {
        if (!hasVoted) {
            perk.upvotes += 1;
            hasVoted = true;
        }
    };

    const incrementDownvotes = () => {
        if (!hasVoted) {
            perk.downvotes += 1;
            hasVoted = true;
        }
    };
</script>

<div class="perk-container">
    <div class="perk-card" class:flipped={flipped}>
        <div class="card-inner" on:click={toggleFlip}>
            <div class="card-front">
                <h2>{perk.name}</h2>
                <div class="votes">
                    <div class="vote-option">
                        <button class="arrow-button" on:click|stopPropagation={incrementUpvotes} disabled={hasVoted}>
                            ⬆️
                        </button>
                        <span class="vote-count upvote">{perk.upvotes}</span>
                    </div>
                    <div class="vote-option">
                        <button class="arrow-button" on:click|stopPropagation={incrementDownvotes} disabled={hasVoted}>
                            ⬇️
                        </button>
                        <span class="vote-count downvote">{perk.downvotes}</span>
                    </div>
                </div>
                <p class="expiry-text">Expiry: {perk.expiryDate}</p>
                <button class="flip-button">{buttonText}</button>
            </div>
            <div class="card-back">
                <h2>Perk Code</h2>
                <p>{perk.code}</p>
            </div>
        </div>
    </div>
</div>

<style>
    /* Centering the card in the viewport */
    .perk-container {
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #f4a261; /* Light background color */
        margin: 0;

    }

    /* Card container with 3D flip effect */
    .perk-card {
        position: relative;
        perspective: 1000px;
        width: 200px;
        height: 320px;
        margin: 30px auto;
        border-radius: 15px;
        background: linear-gradient(145deg, #f7f7f7, #e2e2e2);
    }

    /* Inner card structure with flip effect */
    .card-inner {
        position: relative;
        width: 100%;
        height: 320px; /* Increased height to accommodate all content with padding */
        transition: transform 0.6s ease-in-out;
        transform-style: preserve-3d;
        margin-bottom: 30px;
    }

    .flipped .card-inner {
        transform: rotateY(180deg);
    }

    /* Front and back sides of the card */
    .card-front,
    .card-back {
        position: absolute;
        width: 100%;
        height: 100%; /* Match the height of .card-inner */
        backface-visibility: hidden;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        border-radius: 15px; /* Ensure consistent border radius */
        margin: 0; /* Remove any unintended margins */
    }

    .card-front {
        z-index: 2;
    }

    .card-back {
        transform: rotateY(180deg);
        background: linear-gradient(145deg, #ff9a8b, #ff6a88); /* Backside gradient */
        color: #fff;
    }

    h2 {
        font-size: 22px;
        font-weight: bold;
        margin-bottom: 15px; /* Increased margin */
        color: #333;
    }

    .votes {
        display: flex;
        justify-content: center;
        gap: 30px;
        margin-top: 20px;
    }

    .vote-option {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        font-size: 16px;
    }

    /* Buttons for upvotes and downvotes */
    .arrow-button {
        border: none;
        background: none;
        font-size: 50px;
        cursor: pointer;
        transition: transform 0.2s ease-in-out;
        margin-bottom: 5px;
    }

    .arrow-button:disabled {
        cursor: not-allowed;
        opacity: 0.5;
    }

    .arrow-button:hover:not(:disabled) {
        transform: scale(1.2);
    }

    .vote-count {
        font-weight: bold;
        font-size: 18px;
    }

    .upvote {
        color: #2ecc71; /* Green for upvotes */
    }

    .downvote {
        color: #e74c3c; /* Red for downvotes */
    }

    .expiry-text {
        font-size: 14px;
        color: #777;
        margin-top: 15px;
    }

    .flip-button {
        padding: 12px 25px;
        background-color: #3498db;
        color: white;
        border: none;
        border-radius: 25px;
        cursor: pointer;
        transition: background-color 0.3s ease-in-out;
        margin-top: 20px;
        font-size: 16px;
        font-weight: bold;
    }

    .flip-button:hover {
        background-color: #2980b9;
    }
</style>
