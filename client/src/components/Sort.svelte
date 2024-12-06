<script>
    let selectedOption = "";
    export let parentSortFunction;
    async function callSort() {
    try {
        const response = await fetch(`http://localhost:5173/api/perks/sort/${selectedOption}`, {
            method: 'GET',
        });
        if (response.ok) {
            const sortedPerks = await response.json();
            console.log("response ok for callSort async");
            parentSortFunction(sortedPerks);
        } else {
            console.error('Failed to sort');
        }
        } catch (error) {
            console.error('Error with sorting request: ', error);
        }
    }
</script>

<div class="main">
  <h1>Sort By: </h1>
  <select class="selection" bind:value={selectedOption}>
    <option value="pName">Perk Names (A-Z)</option>
    <option value="pUp">Upvotes</option>
    <option value="pDown">Downvotes</option>
    <option value="pDate">Nearest Expiry Date</option>
  </select>
</div>

<div class="button-section">
  <button on:click={callSort}>Submit</button>
</div>

<style>
  .main {
    background-color: #301818;
    padding: 20px;
    text-align: center;
		color: #FFFFFF;
  }

  .button-section {
    background-color: #189090;
    padding: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100px;
  }
.selection
	{
    background-color: #FFFFFF;
		color: #301818;
    padding: 5px;
		font-size: 14px;
    justify-content: center;
    align-items: center;
		border-radius: 5px;
    height: 40px;
		width: 200px;
	}

  button {
    background-color: #F09048;
    color: white;
    border: none;
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
    border-radius: 5px;
    transition: background-color 0.3s;
  }

  button:hover {
    background-color: #D86000;
  }
</style>
