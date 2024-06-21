<script setup lang="ts">
import { ref } from "vue";
import VueFeather from "vue-feather";
import ExcecutionState from "../ExcecutionState.vue";
import type AppointmentCardType from "@/types/AppointmentCard";
import ModalSee from "@/components/Modal/Appointment/SeeMedicAppointment.vue";
const props = defineProps<{ appointmentCardType: AppointmentCardType; controls?: boolean }>();
const modalSee = ref<typeof ModalSee>();

console.log(props.appointmentCardType.status.toString());
</script>

<template>
  <li class="flex flex-col bg-white">
    <!--Intro-->
    <div class="flex flex-col gap-1 rounded-t-lg border-x border-t border-blue-300 bg-blue-100 p-2">
      <p class="font-bold text-blue-500">Cita Médica</p>
    </div>
    <ExcecutionState
      class="border-x border-blue-300 text-sm"
      :state="props.appointmentCardType.status.toString()"
    />
    <!--Fechas-->
    <div class="flex flex-1 flex-row border-x border-blue-300 p-4">
      <ul class="flex w-full flex-col justify-center gap-2 text-blue-500">
        <li class="flex items-center gap-2">
          <VueFeather
            class="size-10 min-w-10"
            type="calendar"
            stroke="#01193F"
            stroke-width="1.5"
          ></VueFeather>
          <div class="flex flex-col">
            <p class="text-xs font-semibold">Fecha de inicio</p>
            <p class="text-base font-semibold">
              {{ props.appointmentCardType.startDate }}
            </p>
          </div>
        </li>
        <li class="flex items-center gap-2">
          <VueFeather
            class="size-10 min-w-10"
            type="calendar"
            stroke="#01193F"
            stroke-width="1.5"
          ></VueFeather>
          <div class="flex flex-col">
            <p class="text-xs font-semibold">Fecha de fin</p>
            <p class="text-base font-semibold">
              {{ props.appointmentCardType.endDate }}
            </p>
          </div>
        </li>
      </ul>
    </div>
    <div class="flex flex-row justify-end gap-2 rounded-b-lg border-x border-b border-blue-300 p-2">
      <button
        type="button"
        class="inline-flex items-center rounded-lg bg-blue-200 p-2.5 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-300 active:scale-95"
        @click="modalSee?.show()"
      >
        <VueFeather type="eye" stroke-width="3.5" size="16"></VueFeather>
      </button>
    </div>
  </li>
  <ModalSee ref="modalSee" />
</template>
