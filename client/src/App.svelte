<script>
  import { Router, Route } from 'svelte-routing'
  import Home from './components/Home.svelte'
  import Login from './components/Login.svelte'
  import Register from './components/Register.svelte'
  import Profile from './components/Profile.svelte'
<<<<<<< Updated upstream
  import AddPerk from './components/AddPerk.svelte'
=======
>>>>>>> Stashed changes

</script>

<Router>
  <Route path="/" component={Home} />
  <Route path="/login" component={Login} />
  <Route path="/register" component={Register} />
  <Route path="/profile" component={Profile} />
<<<<<<< Updated upstream
  <Route path="/addNewPerk" component={AddPerk} />
</Router>
=======
</Router>
>>>>>>> Stashed changes
