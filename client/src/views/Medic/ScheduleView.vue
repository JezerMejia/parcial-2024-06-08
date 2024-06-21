<script setup lang="ts">
import { onMounted, ref } from "vue";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import ScheduleCard from "@/components/Cards/ScheduleCard.vue";
import type Appointment from "@/types/Appointment";
import ModalAdd from "@/components/Modal/Appointment/CreateAppointment.vue";
const modalAdd = ref<typeof ModalAdd>();
import { getAppointmentByMedic } from "@/composables/useAppointment";
import hasPermission from "@/utils/hasPermission";
import { RoleType } from "@/types/RoleType";
import ForbiddenAlert from "@/components/ForbiddenAlert.vue";

const appointments = ref<Appointment[]>([]);

const isAllowed = hasPermission(RoleType.DOCT);

onMounted(async () => {
  if (!isAllowed) return;

  await fetchUsers();
});

async function fetchUsers() {
  const { data } = await getAppointmentByMedic();
  const record = data.value;

  if (!record || !record.ok) return;
  appointments.value = record.data ?? [];

  console.log(record.data);
  console.log(appointments.value);
}

/*const scheduleRegister: ScheduleType[] = [
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
];*/
</script>

<template>
  <section v-if="isAllowed" class="bg-white p-4">
    <CurrentPageInfo title="Ver Horario Medico" icon="grid" />
    <ul class="grid w-full gap-4 py-4 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
      <ScheduleCard :scheduleCardType="item" :key="index" v-for="(item, index) in appointments" />
    </ul>
  </section>
  <ForbiddenAlert v-else />
  <ModalAdd ref="modalAdd" />
</template>
