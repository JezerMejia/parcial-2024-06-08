<script setup lang="ts">
import type User from "@/types/User";
import PatientCard from "@/components/Cards/PatientCard.vue";
import { useAuthenticatedFetch } from "@/composables/useBaseFetch";
import { onMounted, ref } from "vue";
import type GeneralResponse from "@/types/GeneralResponse";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import hasPermission from "@/utils/hasPermission";
import { RoleType } from "@/types/RoleType";
import ForbiddenAlert from "@/components/ForbiddenAlert.vue";

const users = ref<User[]>([]);

const isAllowed = [RoleType.RECP, RoleType.DOCT].some((p) => hasPermission(p));

onMounted(async () => {
  if (!isAllowed) return;

  const { data } =
    await useAuthenticatedFetch("/api/users/getPatients").json<GeneralResponse<User[]>>();
  const response = data.value;
  users.value = response?.data ?? [];

  console.log(users.value);
});
</script>

<template>
  <section v-if="isAllowed" class="flex w-full flex-col gap-6 rounded-lg bg-white p-4">
    <CurrentPageInfo icon="grid" title="Gestionar Historial" />
    <ul class="grid gap-4 py-4 md:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4">
      <PatientCard v-for="(item, index) in users" :key="index" :patient="item" :asist="false" />
    </ul>
  </section>

  <ForbiddenAlert v-else />
</template>
