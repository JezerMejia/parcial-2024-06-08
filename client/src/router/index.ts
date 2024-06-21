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
          path: "citas",
          name: "appointment",
          component: () => import("../views/AppointmentView.vue"),
        },
        {
          path: "historial",
          name: "historial-usuario",
          component: () => import("../views/Patient/HistoryView.vue"),
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
      path: "/medico",
      component: DefaultLayout,
      children: [
        {
          path: "horario",
          name: "horario",
          component: () => import("../views/Medic/ScheduleView.vue"),
        },
        {
          path: "historial",
          name: "gestionHistorial",
          component: () => import("../views/Doctor/ManageHistoryView.vue"),
        },
        {
          path: "historial/:username",
          name: "gestionHistorialByPatient",
          component: () => import("../views/Doctor/PatientHistoryView.vue"),
        },
        {
          path: "prescripciones",
          name: "verPrescripciones",
          component: () => import("../views/Doctor/PrescriptionView.vue"),
        },
        {
          path: "prescripcion/:username",
          name: "prescripcionHistorialByPatient",
          component: () => import("../views/Doctor/PrescriptionHistoryView.vue"),
        },
      ],
    },
    {
      path: "/asistente",
      component: DefaultLayout,
      children: [
        {
          path: "citas",
          name: "gestionarCitas",
          component: () => import("../views/Assistant/ManageAppointment.vue"),
        },
        {
          path: "historial",
          name: "gestionHistorialAsis",
          component: () => import("../views/Doctor/ManageHistoryView.vue"),
        },
        {
          path: "historial/:username",
          name: "gestionHistorialByPatientAsis",
          component: () => import("../views/Doctor/PatientHistoryView.vue"),
        },
      ],
    },
    {
      path: "/administrador",
      component: DefaultLayout,
      children: [
        {
          path: "asignar-roles",
          name: "asign-role",
          component: () => import("../views/AsignRoleView.vue"),
        },
      ],
    },
  ],
});

export default router;
