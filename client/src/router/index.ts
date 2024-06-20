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
        {
          path: "asignar-rol",
          name: "asign-role",
          component: () => import("../views/AsignRoleView.vue"),
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
  ],
});

export default router;
