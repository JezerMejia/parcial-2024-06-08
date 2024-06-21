<template>
  <main class="flex flex-col gap-6 w-full bg-white p-4 rounded-lg">
    <div class="w-full flex text-blue-500 items-center gap-1">
      <VueFeather type="grid" />
      <h2 class="text-2xl font-black">Gestionar Historial</h2>
    </div>
    <div class="grid grid-cols-5 gap-4">
      <PatientCard />
      <PatientCard />
      <PatientCard />
      <PatientCard />
      <PatientCard />
    </div>
  </main>
</template>
<script setup lang="ts">
import VueFeather from "vue-feather";
import PatientCard from "@/components/Cards/PatientCard.vue";
</script>
