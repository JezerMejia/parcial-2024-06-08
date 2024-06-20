<script setup lang="ts">
import type MedicAppointment from "@/types/MedicAppointment";

const props = defineProps<MedicAppointment>();
</script>

<template>
  <div class="grid grid-cols-2 gap-2">
    <div class="flex flex-col">
      <span>Médico</span>
      <span>{{ props.email }}</span>
    </div>
    <div class="flex flex-col">
      <span>Especialidad</span>
      <span>{{ props.specialtyCode }}</span>
    </div>
  </div>
</template>
