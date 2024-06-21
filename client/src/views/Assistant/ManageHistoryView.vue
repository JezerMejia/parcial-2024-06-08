<template>
  <main class="flex flex-col gap-6 w-full bg-white p-4 rounded-lg">
    <div class="w-full flex text-blue-500 justify-between items-center">
      <div class="flex items-center gap-1">
        <VueFeather type="grid" />
        <h2 class="text-2xl font-black">Gestionar Historial</h2>
      </div>
      <button
        class="flex items-center bg-blue-200 text-blue-400 py-2 px-3 gap-1 rounded-lg mr-2 active:scale-95 transition-all">
        <VueFeather type="plus" />
        <span>Nuevo historial</span>
      </button>
    </div>
    <div class="grid grid-cols-5 gap-4">
      <div v-for="item in users">
        <PatientCard :patient="item" :asist="true" />
      </div>
    </div>
  </main>
</template>
<script setup lang="ts">
import type User from "@/types/User";
import VueFeather from "vue-feather";
import PatientCard from "@/components/Cards/PatientCard.vue";
import { useAuthenticatedFetch } from "@/composables/useBaseFetch";
import { onMounted, ref } from "vue";
import type GeneralResponse from "@/types/GeneralResponse";

const users = ref<User[]>([]);

onMounted(async () => {
  const { data } =
    await useAuthenticatedFetch("/api/users/getPatients").json<GeneralResponse<User[]>>();
  const response = data.value;
  users.value = response?.data ?? [];

  console.log(users.value);
});
</script>
