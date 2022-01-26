<template>
  <div id="app">
    <!-- this navigation will appear on every route -->

    <b-navbar variant="primary" type="dark">
      <b-navbar-brand to="/"><img src="./assets/icconsultlogo.svg" class="logo"></b-navbar-brand>
      <b-navbar-nav class="bar" align="center">
        <b-nav-item to="/">Home</b-nav-item>
        <b-nav-item to="/graphs">Graphs</b-nav-item>
        <b-nav-item to="/tables">Tables</b-nav-item>
      </b-navbar-nav>
    </b-navbar>

    <div class="main">
      <router-view></router-view>
    </div>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  color: #2c3e50;
}

.logo {
  height: 35px;
  margin: 0 3px;
}

.bar {
  height: 35px;
  margin: 0 3px;
}

.main {
  padding: 10px 20px;
}
</style>
