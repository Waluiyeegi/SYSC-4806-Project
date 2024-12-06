<script>
  import { authState } from "../authStore";
  import { Link } from "svelte-routing";
  import { get } from "svelte/store";
  import { onMount } from 'svelte';
  import Perk from './Perk.svelte'; // Import the Perk component
  import API_URL from '../api.js'

  let perks = [];
  let searchQuery = "";
  let selectedGeographicAreas = [];
  let geographicAreas = [];
  let selectAllGeographicAreas = false;
  let isGeographicDropdownOpen = false;

  let memberships = []; // Placeholder
  let selectedMemberships = [];
  let isMembershipDropdownOpen = false;

  let products = []; // List of available products
  let selectedProducts = []; // Currently selected products
  let isProductDropdownOpen = false; // Dropdown visibility state


  // Subscribe to authState
  let { loggedIn, username } = get(authState);

  $: ({ loggedIn, username } = get(authState));

  $: {
      fetchPerksByMembership(selectedMemberships);
  }

  $: {
      fetchPerksByProducts(selectedProducts);
  }


  async function fetchPerks() {
      // Fetch perks from the backend (replace with actual API if needed)
      const response = await fetch(`${API_URL}/api/perks`);
      perks = await response.json();
  }

  onMount(fetchPerks);
  onMount(() => {
      fetchUniqueMemberships();
  });

  $: {
      fetchPerksByGeographicArea(selectedGeographicAreas);
  }


  async function fetchUniqueGeographicAreas() {
      try {
          const response = await fetch(`${API_URL}/api/perks/uniqueGeographicAreas`);
          geographicAreas = await response.json();
          console.log("Fetched geographic areas:", geographicAreas);
      } catch (error) {
          console.error("Error fetching geographic areas:", error);
      }
  }

  async function fetchPerksByGeographicArea(areas) {
      try {
          if (areas.length === 0) {
              // Fetch all perks if no areas are selected
              const response = await fetch(`${API_URL}/api/perks`);
              perks = await response.json();
          } else {
              // Fetch perks for all selected areas
              const response = await fetch(
                  `${API_URL}/api/perks/geographicArea?${areas.map(a => `geographicAreas=${encodeURIComponent(a)}`).join('&')}`
              );
              perks = await response.json();
          }
      } catch (error) {
          console.error("Error fetching perks by geographic area:", error);
      }
  }


  function handleSelectAllGeographicAreas() {
      if (selectAllGeographicAreas) {
          selectedGeographicAreas = [...geographicAreas];
      } else {
          selectedGeographicAreas = [];
      }
  }

  async function fetchUniqueMemberships() {
      try {
          const response = await fetch("http://localhost:5173/api/perks/uniqueMemberships");
          memberships = await response.json();
          console.log("Fetched memberships:", memberships);
      } catch (error) {
          console.error("Error fetching unique memberships:", error);
      }
  }

  async function fetchUniqueProducts() {
      try {
          const response = await fetch(`${API_URL}/api/perks/uniqueProducts`);
          products = await response.json();
          console.log("Fetched products:", products);
      } catch (error) {
          console.error("Error fetching unique products:", error);
      }
  }

  async function fetchPerksByProducts(products) {
      try {
          if (products.length === 0) {
              const response = await fetch(`${API_URL}/api/perks`);
              perks = await response.json();
          } else {
              const response = await fetch(
                  `${API_URL}/api/perks/product?${products
                      .map(p => `products=${encodeURIComponent(p)}`)
                      .join("&")}`
              );
              perks = await response.json();
          }
      } catch (error) {
          console.error("Error fetching perks by products:", error);
      }
  }


  $: {
      console.log("Membership options available:", memberships);
  }


  async function fetchPerksByMembership(memberships) {
      try {
          if (memberships.length === 0) {
              const response = await fetch(`${API_URL}/api/perks`);
              perks = await response.json();
          } else {
              const response = await fetch(
                  `${API_URL}/api/perks/membership?${memberships.map(m => `memberships=${encodeURIComponent(m)}`).join('&')}`
              );
              perks = await response.json();
          }
      } catch (error) {
          console.error("Error fetching perks by membership:", error);
      }
  }




  function toggleGeographicDropdown() {
      isGeographicDropdownOpen = !isGeographicDropdownOpen;
  }

  function toggleMembershipDropdown() {
      isMembershipDropdownOpen = !isMembershipDropdownOpen;
  }

  function toggleProductDropdown() {
      isProductDropdownOpen = !isProductDropdownOpen;
  }


onMount(() => {
    fetchUniqueGeographicAreas();
    fetchUniqueProducts();
    fetchUniqueMemberships();
});


  function logOut() {
      authState.set({
          loggedIn: false,
          username: "",
      });
      location.href = "/";
  }

</script>

<main>
    <div class="home-page">
        <div class="top-bar">
            <div class="top-bar-left">
                <h1 class="title">Perk Manager</h1>
            </div>
            <div class="top-bar-right">
                {#if loggedIn}
                    <Link to="/profile"><button>Profile</button></Link>
                    <button on:click={logOut}>Log Out</button>
                {:else}
                    <Link to="/login"><button>Log In</button></Link>
                    <Link to="/register"><button>Register</button></Link>
                {/if}
            </div>
        </div>
        <div class="content-section">
            <!--{#if loggedIn}-->
                <div class="filter-section">
                    <h3>Filter Section</h3>
                    <!-- Add filter UI here -->
                    <div id="search-area">
                        <label for="search">Search:</label>
                        <input
                                type="text"
                                id="search"
                                placeholder="Search for perks..."
                                bind:value={searchQuery}
                        />

                    </div>

                    <style>
                        #search-area {
                            margin-bottom: 10px;
                        }

                        label {
                            font-weight: bold;
                            display: block;
                            margin: 10px 0 5px;
                        }

                        input[type="text"] {
                            width: 100%;
                            padding: 8px;
                            margin-bottom: 10px;
                        }
                    </style>

                    <div id="geographic-area-filter">
                    <label for="geographic-area-dropdown" class="dropdown-label" on:click={toggleGeographicDropdown}>
                        Geographic Area:
                        <span class="dropdown-arrow">{isGeographicDropdownOpen ? "▲" : "▼"}</span>
                    </label>

                    {#if isGeographicDropdownOpen}
                        <div class="geographic-checkbox-group">
                            {#each geographicAreas as area}
                                <div>
                                    <input
                                            type="checkbox"
                                            id="geographic-{area}"
                                            value={area}
                                            bind:group={selectedGeographicAreas}
                                    />
                                    <label for="geographic-{area}">{area}</label>
                                </div>
                            {/each}
                        </div>
                    {/if}
                </div>


                    <style>
                        .dropdown-label {
                            cursor: pointer;
                            font-weight: bold;
                            display: flex;
                            justify-content: space-between;
                            align-items: center;
                            padding: 10px;
                            border: 1px solid #ccc;
                            background-color: #fff;
                            border-radius: 5px;
                            user-select: none;
                        }

                        .dropdown-arrow {
                            font-size: 14px;
                            color: #888;
                        }

                        #geographic-area-filter {
                            margin-bottom: 20px;
                        }

                        .geographic-checkbox-group {
                            border: 1px solid #ccc;
                            background-color: #fff;
                            border-radius: 5px;
                            margin-top: 5px;
                            padding: 10px;
                            display: flex;
                            flex-direction: column;
                            max-height: 150px;
                            overflow-y: auto;
                        }

                        .geographic-checkbox-group div {
                            display: flex;
                            align-items: center;
                            margin-bottom: 5px;
                        }

                        input[type="checkbox"] {
                            margin-right: 8px;
                        }

                        label {
                            font-size: 14px;
                            color: #333;
                        }

                        /* Optional: Add hover effects to make the dropdown more interactive */
                        .dropdown-label:hover {
                            background-color: #f0f0f0;
                        }
                    </style>

                    <div id="membership-filter">
                        <label for="membership-dropdown" class="dropdown-label" on:click={toggleMembershipDropdown}>
                            Memberships:
                            <span class="dropdown-arrow">{isMembershipDropdownOpen ? "▲" : "▼"}</span>
                        </label>

                        {#if isMembershipDropdownOpen}
                            <div class="membership-checkbox-group">
                                {#each memberships as membership}
                                    <div>
                                        <input
                                                type="checkbox"
                                                id="membership-{membership}"
                                                value={membership}
                                                bind:group={selectedMemberships}
                                        />
                                        <label for="membership-{membership}">{membership}</label>
                                    </div>
                                {/each}
                            </div>
                        {/if}
                    </div>
                    <style>
                        /* Membership Filter Styles */
                        #membership-filter {
                            margin-bottom: 20px;
                        }

                        .membership-checkbox-group {
                            border: 1px solid #ccc;
                            background-color: #fff;
                            border-radius: 5px;
                            margin-top: 5px;
                            padding: 10px;
                            display: flex;
                            flex-direction: column;
                            max-height: 150px;
                            overflow-y: auto;
                        }

                        .membership-checkbox-group div {
                            display: flex;
                            align-items: center;
                            margin-bottom: 5px;
                        }

                        input[type="checkbox"] {
                            margin-right: 8px;
                        }

                        label {
                            font-size: 14px;
                            color: #333;
                        }

                        .dropdown-label {
                            cursor: pointer;
                            font-weight: bold;
                            display: flex;
                            justify-content: space-between;
                            align-items: center;
                            padding: 10px;
                            border: 1px solid #ccc;
                            background-color: #fff;
                            border-radius: 5px;
                            user-select: none;
                        }

                        .dropdown-arrow {
                            font-size: 14px;
                            color: #888;
                        }

                        .dropdown-label:hover {
                            background-color: #f0f0f0;
                        }
                    </style>


                    <div id="product-filter">
                        <label
                                for="product-dropdown"
                                class="dropdown-label"
                                on:click={toggleProductDropdown}
                        >
                            Products:
                            <span class="dropdown-arrow">{isProductDropdownOpen ? "▲" : "▼"}</span>
                        </label>

                        {#if isProductDropdownOpen}
                            <div class="product-checkbox-group">
                                {#each products as product}
                                    <div>
                                        <input
                                                type="checkbox"
                                                id="product-{product}"
                                                value={product}
                                                bind:group={selectedProducts}
                                        />
                                        <label for="product-{product}">{product}</label>
                                    </div>
                                {/each}
                            </div>
                        {/if}
                    </div>

                    <style>
                        .dropdown-label {
                            cursor: pointer;
                            font-weight: bold;
                            display: flex;
                            justify-content: space-between;
                            align-items: center;
                            padding: 10px;
                            border: 1px solid #ccc;
                            background-color: #fff;
                            border-radius: 5px;
                            user-select: none;
                        }

                        .dropdown-label:hover {
                            background-color: #f0f0f0;
                        }

                        .dropdown-arrow {
                            font-size: 14px;
                            color: #888;
                        }

                        .product-checkbox-group {
                            border: 1px solid #ccc;
                            background-color: #fff;
                            border-radius: 5px;
                            margin-top: 5px;
                            padding: 10px;
                            display: flex;
                            flex-direction: column;
                            max-height: 150px; /* Ensure a fixed height for scrolling */
                            overflow-y: auto; /* Enable scrolling when content overflows */
                            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                        }

                        /* Optional: Add a scrollbar style for better visuals */
                        .product-checkbox-group::-webkit-scrollbar {
                            width: 8px;
                        }

                        .product-checkbox-group::-webkit-scrollbar-thumb {
                            background-color: #ccc;
                            border-radius: 4px;
                        }

                        .product-checkbox-group::-webkit-scrollbar-thumb:hover {
                            background-color: #aaa;
                        }

                        .product-checkbox-group div {
                            display: flex;
                            align-items: center;
                            margin-bottom: 5px;
                        }

                        input[type="checkbox"] {
                            margin-right: 8px;
                        }

                        label {
                            font-size: 14px;
                            color: #333;
                        }
                    </style>

                </div>
            <!--{/if}-->
            <div class="perk-list-section">
                <Link to="/addNewPerk"><button class="add-perk-btn">Add Perk</button></Link>
                <div class="perk-list">
                    {#if perks.length > 0}
                        {#each perks as perk (perk.id)}
                            <Perk {perk} />
                        {/each}
                    {:else}
                        <p>No perks found for the selected memberships.</p>
                    {/if}
                </div>
            </div>
        </div>
    </div>
</main>

<style>
    main {
        height: 100%;
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #f9f9f9;
    }

    .home-page {
        display: flex;
        flex-direction: column;
        height: 100vh;
        width: 100vw;
        overflow: hidden;
    }

    .title {
        font-size: 28px; /* Large font for the title */
        font-weight: bold;
        color: #ffffff;
        margin: 0;
        padding: 0;
    }

    .top-bar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        background-color: #333;
        color: white;
        padding: 1rem;
        z-index: 100;
    }

    .top-bar-right button {
        background-color: #2a9d8f;
        color: white;
        padding: 0.5rem 1rem;
        cursor: pointer;
        border: none;
        border-radius: 5px;
        transition: background-color 0.3s;
    }

    .top-bar-right button:hover {
        background-color: #264653;
    }

    .content-section {
        padding-top: 60px;
        display: flex; /* Ensure horizontal alignment */
        flex-grow: 1;
        width: 100%;
        height: calc(100vh - 60px);
    }

    .filter-section {
        background-color: #2a9d8f;
        padding: 2rem;
        color: white;
        width: 25%; /* Take up 25% of the space */
        max-width: 300px;
        box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
    }

    .perk-list-section {
        background-color: #ffffff;
        padding: 2rem;
        flex-grow: 1; /* Take up remaining space */
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        display: flex;
        flex-direction: column;
        align-items: center;
        overflow-y: auto;
    }

    .perk-list {
        display: grid;
        grid-template-columns: repeat(4, 1fr);
        gap: 1.5rem;
        width: 100%;
    }

    .add-perk-btn {
        background-color: #e76f51;
        color: white;
        border: none;
        padding: 0.8rem 1.5rem;
        cursor: pointer;
        border-radius: 5px;
        font-size: 16px;
        font-weight: bold;
        margin-bottom: 1.5rem;
        transition: background-color 0.3s;
    }

    .add-perk-btn:hover {
        background-color: #f4a261;
    }

    @media (max-width: 768px) {
        .content-section {
            flex-direction: column;
        }

        .filter-section {
            width: 100%;
            max-width: none;
            box-shadow: none;
        }

        .perk-list {
            grid-template-columns: 1fr 1fr;
        }
    }

    @media (max-width: 480px) {
        .perk-list {
            grid-template-columns: 1fr;
        }
    }
</style>
