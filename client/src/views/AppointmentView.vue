<script setup lang="ts">
import { onMounted, ref } from "vue";
import VueFeather from "vue-feather";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import AppointmentCard from "@/components/Cards/AppointmentCard.vue";
import type Appointment from "@/types/Appointment";
import ModalAdd from "@/components/Modal/Appointment/CreateAppointment.vue";
const modalAdd = ref<typeof ModalAdd>();
import { getOwnAppointments } from "@/composables/useAppointment";
import { useUser } from "@/stores/user";
import hasPermission from "@/utils/hasPermission";
import { RoleType } from "@/types/RoleType";

const appointments = ref<Appointment[]>([]);

const user = useUser();

onMounted(async () => {
  if (!hasPermission(RoleType.PTNT)) return;

  await fetchUsers();
});

async function fetchUsers() {
  const { data } = await getOwnAppointments();
  const record = data.value;

  if (!record || !record.ok) return;
  appointments.value = record.data ?? [];

  console.log(appointments.value);
}
</script>

<template>
  <section class="bg-white p-4">
    <CurrentPageInfo title="Ver mis Citas" icon="grid">
      <button
        class="inline-flex items-center rounded-lg bg-blue-200 p-2 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-300 active:scale-95"
        @click="modalAdd?.show()"
      >
        <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
        <span>Crear Cita</span>
      </button>
    </CurrentPageInfo>

    <ul
      class="grid gap-4 py-4 md:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4"
      v-if="user.user?.roles.includes('PTNT')"
    >
      <AppointmentCard :appointment="item" :key="index" v-for="(item, index) in appointments" />
    </ul>
  </section>
  <ModalAdd ref="modalAdd" />
</template>
