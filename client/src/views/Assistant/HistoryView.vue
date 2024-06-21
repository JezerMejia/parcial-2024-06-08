<script setup lang="ts">
import PatientCard from "@/components/Cards/PatientCard.vue";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import type PatientCardType from "@/types/PatientCard";

const patientCardType: PatientCardType[] = [
  {
    username: "Jose",
    entries: "12",
  },
  {
    username: "Juan",
    entries: "12",
  },
  {
    username: "Pedro",
    entries: "12",
  },
];
</script>

<template>
  <div class="bg-white p-4">
    <div class="flex flex-row items-center justify-between">
      <CurrentPageInfo title="Historial Medico" icon="grid" />
    </div>
    <ul class="grid w-full gap-4 py-4 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
      <PatientCard :patientCardType="item" :key="index" v-for="(item, index) in patientCardType" />
    </ul>
  </div>
</template>
