<script setup lang="ts">
import { getPrescriptions } from "@/composables/usePrescription";
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

const url = `/medico/prescripcion/${props.patient.username}`;
</script>
<template>
  <li class="flex flex-col rounded-[4px] border border-blue-300 bg-blue-100">
    <div class="p-2">
      <h3 class="font-black">Paciente</h3>
      <div class="flex items-center gap-1">
        <div class="flex size-5 items-center justify-center rounded-full border border-blue-400">
          <VueFeather class="size-[10px] text-blue-400" type="user" />
        </div>
        <p class="font-light text-blue-400">{{ props.patient.username }}</p>
      </div>
      <p class="font-medium">{{ prescriptionsList.length }} prescripciones</p>
    </div>
    <div class="flex w-full justify-end rounded-b-[4px] bg-white p-2">
      <a :href="url" class="flex size-[34px] items-center justify-center rounded-lg bg-blue-200 active:scale-95">
        <VueFeather type="eye" class="size-[18px] font-black" />
      </a>
    </div>
  </li>
</template>
