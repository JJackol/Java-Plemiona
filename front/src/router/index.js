import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Layout from "../views/Layout.vue";
import WioskaView from "../views/WioskaView.vue";
import LoginView from "../views/LoginView.vue";
import PlayerView from "../views/PlayerView";
import ReportsView from "../views/ReportsView";

Vue.use(VueRouter);

const routes = [
  {
    path: "/login",
    name: "Login",
    component: LoginView
  },
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/",
    name: "Layout",
    component: Layout,
    children: [
      {
        path: "/wioska",
        name: "Wioska",
        component: WioskaView
      },
      {
        path: "/reports",
        name: "Raporty",
        component: ReportsView
      },
      {
        path: "/player",
        name: "PlayerView",
        component: PlayerView
      }
    ]
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

// router.beforeEach((to, from, next) => {
//     const loginPage = "/login";
//     if (to.path !== loginPage) {
//         if (store.getters["context/is_logged_in"]) {
//             return next();
//         }
//         return next(loginPage);
//     }
//     return next();
// });

export default router;
