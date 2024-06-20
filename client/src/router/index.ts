import { createRouter, createWebHistory } from "vue-router";
import BlankLayout from "@/layouts/BlankLayout.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      component: BlankLayout,
      children: [
        {
          path: "/",
          name: "page-home",
          component: () => import("../views/HomeView.vue"),
        },
        {
          path: "login",
          name: "login",
          component: () => import("../views/HomeView.vue"),
        },
      ],
    },
    {
      path: "/auth",
      component: BlankLayout,
      children: [
        {
          path: "login",
          name: "login",
          component: () => import("../views/LoginView.vue"),
        },
        {
          path: "register",
          name: "register",
          component: () => import("../views/RegisterView.vue"),
        },
      ],
    },
  ],
});

export default router;
