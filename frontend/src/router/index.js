import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Graphs from "../views/Graphs.vue";
import Cats from "../views/Cats.vue";
import GraphCard from "../components/GraphCard.vue";

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
  }
];

const router = new VueRouter({
  routes,
  components: {
    GraphCard
  }
});

export default router;
