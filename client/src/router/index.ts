import { createRouter, createWebHistory } from "vue-router";
import BlankLayout from "@/layouts/BlankLayout.vue";
import DefaultLayout from "@/layouts/DefaultLayout.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      component: DefaultLayout,
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
    {
      path: "/usuario",
      component: DefaultLayout,
      children: [
        {
          path: "citas",
          name: "citas",
          component: () => import("../views/User/AppointmentView.vue"),
        },
      ],
    },
    {
      path: "/doctor",
      component: DefaultLayout,
      children: [
        {
          path: "gestionHistorial",
          name: "gestionHistorial",
          component: () => import("../views/Doctor/ManageHistoryView.vue"),
        },
      ],
    }
  ],
});

export default router;
