<template>
  <main class="flex flex-col gap-6 w-full bg-white p-4 rounded-lg">
    <div class="w-full flex text-blue-500 justify-between items-center">
      <div class="flex items-center gap-1">
        <VueFeather type="grid" />
        <h2 class="text-2xl font-black">Ver prescripciones</h2>
      </div>
    </div>
    <div class="grid grid-cols-5 gap-4">
      <div v-for="item in users">
        <PrescriptionCard :patient="item" :asist="false" />
      </div>
    </div>
  </main>
</template>
<script setup lang="ts">
import VueFeather from "vue-feather";
import PrescriptionCard from "@/components/Cards/PrescriptionCard.vue";
import { onMounted, ref } from "vue";
import type User from "@/types/User";
import { useAuthenticatedFetch } from "@/composables/useBaseFetch";
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
