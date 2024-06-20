<script setup lang="ts">
import { ref } from "vue";
import VueFeather from "vue-feather";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import AppointmentCard from "@/components/Cards/AppointmentCard.vue";
import type Appointment from "@/types/AppointmentCard";
import { ExecutionState as ExcecutionStateType } from "@/types/ExecutionState";
import ModalAdd from "@/components/Modal/Appointment/CreateAppointment.vue";
const modalAdd = ref<typeof ModalAdd>();

const appointmentHistory: Appointment[] = [
  {
    status: ExcecutionStateType.CANCELED,
    startDate: new Date("2024-01-01"),
    endDate: new Date("2024-01-29"),
  },
  {
    status: ExcecutionStateType.FINISHED,
    startDate: new Date("2024-01-01"),
    endDate: new Date("2024-01-29"),
  },
  {
    status: ExcecutionStateType.PEND_APR,
    startDate: new Date("2024-01-01"),
    endDate: new Date("2024-01-29"),
  },
];
</script>

<template>
  <div class="bg-white p-4">
    <div class="flex flex-row items-center justify-between">
      <CurrentPageInfo title="Ver mis Citas" icon="grid" />
      <button
        class="inline-flex items-center rounded-lg bg-blue-200 p-2 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-300 active:scale-95"
        @click="modalAdd?.show()">
        <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
        <span>Crear Cita</span>
      </button>
    </div>

    <ul class="grid w-full gap-4 py-4 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
      <AppointmentCard :appointmentCardType="item" :key="index" v-for="(item, index) in appointmentHistory" />
    </ul>
  </div>
  <ModalAdd ref="modalAdd" />
</template>