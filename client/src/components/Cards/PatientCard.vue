<script setup lang="ts">
import VueFeather from "vue-feather";
import type PatientCardType from "@/types/PatientCard";
const props = defineProps<{ patientCardType: PatientCardType; controls?: boolean }>();

console.log(props);
</script>

<template>
  <div class="flex flex-col rounded-[4px] border border-blue-300 bg-blue-100">
    <div class="p-2">
      <h3 class="font-black">Paciente</h3>
      <div class="flex items-center gap-1">
        <div class="flex size-5 items-center justify-center rounded-full border border-blue-400">
          <VueFeather class="size-[10px] text-blue-400" type="user" />
        </div>
        <p class="font-medium text-blue-400">{{ props.patientCardType.username }}</p>
      </div>
      <p class="font-medium">{{ props.patientCardType.entries }} entradas</p>
    </div>
    <div class="flex w-full justify-end rounded-b-[4px] bg-white p-2">
      <a
        href="/asistente/historial/gestionar"
        class="inline-flex items-center rounded-lg bg-blue-200 p-2.5 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-300 active:scale-95"
      >
        <VueFeather type="eye" class="size-[18px]" stroke-width="2.5" />
      </a>
    </div>
  </div>
</template>
