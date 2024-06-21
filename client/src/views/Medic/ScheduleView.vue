<script setup lang="ts">
import { ref } from "vue";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import ScheduleCard from "@/components/Cards/ScheduleCard.vue";
import type ScheduleType from "@/types/ScheduleCard";
import { ExecutionState as ExcecutionStateType } from "@/types/ExecutionState";
import ModalAdd from "@/components/Modal/Appointment/CreateAppointment.vue";
const modalAdd = ref<typeof ModalAdd>();

const scheduleRegister: ScheduleType[] = [
  {
    username: "Jose",
    status: ExcecutionStateType.CANCELED,
    startDate: new Date("2024-01-01"),
    endDate: new Date("2024-01-29"),
  },
  {
    username: "Martinez",
    status: ExcecutionStateType.CANCELED,
    startDate: new Date("2024-01-01"),
    endDate: new Date("2024-01-29"),
  },
  {
    username: "Santiago",
    status: ExcecutionStateType.CANCELED,
    startDate: new Date("2024-01-01"),
    endDate: new Date("2024-01-29"),
  },

];
</script>

<template>
  <div class="bg-white p-4">
    <div class="flex flex-row items-center justify-between">
      <CurrentPageInfo title="Ver Horario Medico" icon="grid" />        
    </div>

    <ul class="grid w-full gap-4 py-4 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
      <ScheduleCard :scheduleCardType="item" :key="index" v-for="(item, index) in scheduleRegister" />
    </ul>
  </div>
  <ModalAdd ref="modalAdd" />
</template>