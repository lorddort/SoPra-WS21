import Vue from "vue";
import VueRouter from "vue-router";
import Graphs from "../views/Graphs.vue";
import Tables from "../views/Tables.vue";
import GraphCard from "../components/GraphCard.vue";
import Table from "../components/Table.vue";
import Home from "../views/Home.vue";
import HomeDetails from "../views/HomeDetails.vue";

Vue.use(VueRouter);

// all frontend routes of the app, i.e. bind a path to a Vue component
const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/correlation",
    name: "Tables",
    component: Tables
  },
  {
    path: "/graphs",
    name: "Graphs",
    component: Graphs
  },
  {
    path: "/:id",
    name: "HomeDetails",
    component: HomeDetails
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
