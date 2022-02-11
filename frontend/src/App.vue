<template>
  <div id="app">
    <!-- this navigation will appear on every route #84db00-->

    <b-navbar class="bg-primary" type="dark">
      <b-navbar-brand to="/"><img src="./assets/logo4.png" class="logo"></b-navbar-brand>
      <b-navbar-nav class="bar" align="center">
        <b-nav-item to="/">Home</b-nav-item>
        <b-nav-item to="/graphs">Graphs</b-nav-item>
        <b-nav-item to="/correlation">Correlation</b-nav-item>
      </b-navbar-nav>
    </b-navbar>

    <div class="main">
      <router-view></router-view>
    </div>
  </div>
</template>

<style>

.logo {
  height: 40px;
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
