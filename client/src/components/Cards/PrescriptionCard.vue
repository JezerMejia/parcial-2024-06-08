<script setup lang="ts">
import { useAuthenticatedFetch } from "@/composables/useBaseFetch";
import { getPrescriptions } from "@/composables/usePrescription";
import type Appointment from "@/types/Appointment";
import type GeneralResponse from "@/types/GeneralResponse";
import type Prescription from "@/types/Prescription";
import type User from "@/types/User";
import { onMounted, ref } from "vue";
import VueFeather from "vue-feather";
const props = defineProps<{
  patient: User;
}>();

const prescriptionsList = ref<Prescription[]>([]);

onMounted(async () => {
  const { data } = await getPrescriptions(props.patient.username as string);
  prescriptionsList.value = data.value?.data ?? [];
});

const url = `/doctor/prescripcionHistorial/${props.patient.username}`;
</script>
<template>
  <div class="flex flex-col bg-blue-100 rounded-[4px] border border-blue-300">
    <div class="p-2">
      <h3 class="font-black">Paciente</h3>
      <div class="flex items-center gap-1">
        <div class="flex items-center size-5 justify-center rounded-full border border-blue-400">
          <VueFeather class="size-[10px] text-blue-400" type="user" />
        </div>
        <p class="font-light text-blue-400">{{ props.patient.username }}</p>
      </div>
      <p class="font-medium">{{ prescriptionsList.length }} prescripciones</p>
    </div>
    <div class="flex justify-end p-2 bg-white w-full rounded-b-[4px]">
      <a :href="url" class="size-[34px] bg-blue-200 flex items-center justify-center rounded-lg active:scale-95">
        <VueFeather type="eye" class="size-[18px] font-black" />
      </a>
    </div>
  </div>
</template>
