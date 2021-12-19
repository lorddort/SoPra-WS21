import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Graphs from "../views/Graphs.vue";
import Cats from "../views/Cats.vue";
import Tables from "../views/Tables.vue";
import GraphCard from "../components/GraphCard.vue";
import Table from "../components/Table.vue";

Vue.use(VueRouter);

// all frontend routes of the app, i.e. bind a path to a Vue component
const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/graphs",
    name: "Graphs",
    component: Graphs
  },
  {
    path: "/cats",
    name: "Cats",
    component: Cats
  },
  {
    path: "/tables",
    name: "Tables",
    component: Tables
  }
];

const router = new VueRouter({
  routes,
  components: {
    GraphCard,
    Table
  }
});

export default router;
