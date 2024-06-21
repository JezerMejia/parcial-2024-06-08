<script setup lang="ts">
import { ref } from "vue";
import VueFeather from "vue-feather";
import ExcecutionState from "../ExcecutionState.vue";
import type Appointment from "@/types/Appointment";
import getFormattedDateTime from "@/utils/getFormattedDateTime";
import ModalSee from "@/components/Modal/Appointment/SeeMedicAppointment.vue";
import RejectAppointment from "../Modal/Appointment/RejectAppointment.vue";
import ApproveAppointment from "../Modal/Appointment/ApproveAppointment.vue";

const props = defineProps<{ appointmentCardType: Appointment; controls?: boolean ; showUser? : boolean}>();
const modalSee = ref<typeof ModalSee>();
const modalReject = ref<typeof RejectAppointment>();
const modalApprove = ref<typeof ApproveAppointment>();

</script>

<template>
  <li class="flex flex-col rounded-lg border border-blue-300 bg-white">
    <!--Intro-->
    <div class="flex flex-col gap-1 border-blue-300 bg-blue-100 p-2">
      <p class="font-bold text-blue-500">Cita Médica</p>
      <div v-if="showUser" class="flex items-center gap-1">
        <div class="flex size-5 items-center justify-center rounded-full border border-blue-400">
          <VueFeather class="size-[10px] text-blue-400" type="user" />
        </div>
        <p class="text-blue-400">{{ props.appointmentCardType.patient.username }}</p>
      </div>
    </div>
    <ExcecutionState
      class="text-sm"
      :state="props.appointmentCardType.status"
    />
    <!--Fechas-->
    <div class="flex flex-1 flex-row  p-4">
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
            <p class="text-base">
              {{ getFormattedDateTime(new Date(props.appointmentCardType.startDate)) }}
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
            <p class="text-base">
              {{ getFormattedDateTime(new Date(props.appointmentCardType.endDate)) }}
            </p>
          </div>
        </li>
      </ul>
    </div>
    <div class="flex flex-row justify-end gap-2 p-2">
      <button
        v-if="controls"
        type="button"
        class="inline-flex items-center gap-1 rounded-lg bg-red-200 p-2.5 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-300 active:scale-95"
        @click="modalReject?.show()"
      >
        <VueFeather type="x" stroke-width="3.5" size="16"></VueFeather>
        <span>Rechazar</span>
      </button>
      <button
        v-if="controls"
        type="button"
        class="inline-flex items-center gap-1 rounded-lg bg-green-200 p-2.5 text-center text-sm font-normal text-green-400 transition-all hover:rounded-xl hover:bg-green-300 active:scale-95"
        @click="modalApprove?.show()"
      >
        <VueFeather type="check" stroke-width="3.5" size="16"></VueFeather>
        <span>Aprobar</span>
      </button>
      <button
        type="button"
        class="inline-flex items-center rounded-lg bg-blue-200 p-2.5 px-3 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-300 active:scale-95"
        @click="modalSee?.show()"
      >
        <VueFeather type="eye" stroke-width="3.5" size="16"></VueFeather>
      </button>
    </div>
  </li>
  <ModalSee :medics="props.appointmentCardType.medics" ref="modalSee" />
  <RejectAppointment ref="modalReject" :uuid="appointmentCardType.uuid" />
  <ApproveAppointment ref="modalApprove" :uuid="appointmentCardType.uuid" />
</template>
