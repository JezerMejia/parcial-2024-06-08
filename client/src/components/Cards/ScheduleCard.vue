<script setup lang="ts">
import { ref } from "vue";
import VueFeather from "vue-feather";
import ExcecutionState from "../ExcecutionState.vue";
import type Appointment from "@/types/Appointment";
import SeeAppointment from "@/components/Modal/Appointment/SeeMedicAppointment.vue";
import getFormattedDateTime from "@/utils/getFormattedDateTime";
import FinishAppointment from "../Modal/Appointment/FinishAppointment.vue";
import StartAppointment from "../Modal/Appointment/StartAppointment.vue";
import { ExecutionState } from "@/types/ExecutionState";
const props = defineProps<{ appointment: Appointment; controls?: boolean }>();

const seeAppointment = ref<InstanceType<typeof SeeAppointment>>();
const startAppointment = ref<InstanceType<typeof FinishAppointment>>();
const finishAppointment = ref<InstanceType<typeof FinishAppointment>>();
</script>

<template>
  <li class="flex flex-col overflow-hidden rounded-lg border border-blue-300 bg-white">
    <!--Intro-->
    <div class="flex flex-col gap-1 bg-blue-100 p-2">
      <p class="font-bold text-blue-500">Horario Médico</p>
      <div class="flex items-center gap-1 font-medium text-blue-400">
        <span class="grid place-items-center rounded-full border border-blue-400 bg-blue-200 p-1">
          <VueFeather class="my-auto size-4" type="user" />
        </span>
        <p>{{ props.appointment.patient.username }}</p>
      </div>
    </div>
    <ExcecutionState class="text-sm" :state="props.appointment.status" />
    <p class="line-clamp-2 px-4 py-2">
      {{ props.appointment.reason }}
    </p>
    <!--Fechas-->
    <div class="flex flex-1 flex-row p-4">
      <ul class="flex w-full flex-col justify-center gap-2 text-blue-500">
        <li class="flex items-center gap-2">
          <VueFeather class="size-10 min-w-10" type="calendar" stroke="#01193F" stroke-width="1.5">
          </VueFeather>
          <div class="flex flex-col">
            <p class="text-xs font-semibold">Fecha Inicio</p>
            <p class="text-base">
              {{ getFormattedDateTime(new Date(props.appointment.startDate)) }}
            </p>
          </div>
        </li>
        <li class="flex items-center gap-2">
          <VueFeather class="size-10 min-w-10" type="calendar" stroke="#01193F" stroke-width="1.5">
          </VueFeather>
          <div class="flex flex-col">
            <p class="text-xs font-semibold">Fecha de fin</p>
            <p class="text-base">
              {{ getFormattedDateTime(new Date(props.appointment.endDate)) }}
            </p>
          </div>
        </li>
      </ul>
    </div>
    <div class="flex flex-row justify-end gap-2 p-2">
      <button
        v-if="props.appointment.status == ExecutionState.IN_EXC"
        type="button"
        class="inline-flex items-center rounded-lg bg-red-200 p-2.5 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-300 active:scale-95"
        @click="finishAppointment?.show()"
      >
        <VueFeather type="user-minus" stroke-width="2.5" size="16"></VueFeather>
      </button>
      <button
        v-if="props.appointment.status == ExecutionState.PEND_EXC"
        type="button"
        class="inline-flex items-center rounded-lg bg-green-200 p-2.5 text-center text-sm font-normal text-green-400 transition-all hover:rounded-xl hover:bg-green-300 active:scale-95"
        @click="startAppointment?.show()"
      >
        <VueFeather type="check" stroke-width="2.5" size="16"></VueFeather>
      </button>
      <button
        type="button"
        class="inline-flex items-center rounded-lg bg-blue-200 p-2.5 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-300 active:scale-95"
        @click="seeAppointment?.show()"
      >
        <VueFeather type="eye" stroke-width="2.5" size="16"></VueFeather>
      </button>
    </div>
  </li>
  <SeeAppointment ref="seeAppointment" :medics="props.appointment.medics" />
  <StartAppointment ref="startAppointment" :uuid="props.appointment.uuid" />
  <FinishAppointment ref="finishAppointment" :uuid="props.appointment.uuid" />
</template>
