<script setup lang="ts">
import SquareButton from "@/components/SquareButton.vue";
import { RoleType } from "@/types/RoleType";
import hasPermission from "@/utils/hasPermission";

interface RouteProps {
  title: string;
  icon: "folder" | "settings" | "eye" | "calendar";
  href: string;
  permissions: RoleType[];
}

const routes: RouteProps[] = [
  {
    title: "Ver mis citas",
    icon: "folder",
    href: "/citas",
    permissions: [RoleType.ADMN, RoleType.DOCT, RoleType.PTNT, RoleType.RECP],
  },
  {
    title: "Ver horario medico",
    icon: "eye",
    href: "/medico/horario",
    permissions: [RoleType.DOCT],
  },
  {
    title: "Gestionar historial médico",
    icon: "settings",
    href: "/medico/historial",
    permissions: [RoleType.DOCT],
  },
  {
    title: "Gestionar historial médico",
    icon: "settings",
    href: "/asistente/historial",
    permissions: [RoleType.RECP],
  },
  {
    title: "Gestionar citas",
    icon: "settings",
    href: "/asistente/citas",
    permissions: [RoleType.RECP],
  },
  {
    title: "Ver prescripciones",
    icon: "eye",
    href: "/medico/prescripciones",
    permissions: [RoleType.DOCT],
  },
  {
    title: "Gestionar especialidades",
    icon: "settings",
    href: "/",
    permissions: [RoleType.RECP],
  },
  {
    title: "Asignar roles",
    icon: "settings",
    href: "/administrador/asignar-roles",
    permissions: [RoleType.ADMN],
  },
];

const allowedRoutes = routes.filter((route) =>
  route.permissions.some((permission) => hasPermission(permission))
);
</script>
<template>
  <section class="flex flex-1 flex-wrap items-center justify-center gap-3">
    <SquareButton
      v-for="(route, index) in allowedRoutes"
      :title="route.title"
      :href="route.href"
      :icon="route.icon"
      :key="index"
    />
  </section>
</template>
