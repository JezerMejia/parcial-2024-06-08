<script setup lang="ts">
import CurrentPageInfo from '@/components/CurrentPageInfo.vue';
import { getOwnAppointments } from '@/composables/useAppointment';
import type Appointment from '@/types/Appointment';
import { onMounted, ref } from 'vue';
import AppointmentCard from '@/components/Cards/AppointmentCard.vue';
import VueFeather from "vue-feather";
import hasPermission from '@/utils/hasPermission';
import { RoleType } from '@/types/RoleType';
import ForbiddenAlert from '@/components/ForbiddenAlert.vue';

const finalAppoinments = ref<Appointment[]>([])
const isAllowed = hasPermission(RoleType.RECP)

onMounted(() => {

  if(!isAllowed) return;

  fetchAppointments();

  setInterval(async () => {
    await fetchAppointments();
  }, 30000);
})

async function fetchAppointments() {
  const { data } = (await getOwnAppointments());
  finalAppoinments.value = data.value?.data ?? [];
}


</script>
<template>
  <section v-if="isAllowed" class="rounded-md bg-white p-4">
    <CurrentPageInfo icon="grid" title="Gestionar Citas">
      <button @click="fetchAppointments"
        class="group grid aspect-square h-full min-h-9 w-auto place-content-center rounded-lg bg-green-200 text-green-400 transition-all hover:rounded-xl hover:bg-green-300 active:scale-95">
        <VueFeather class="size-[18px] transition-all group-active:rotate-[32deg]" type="loader" />
      </button>
    </CurrentPageInfo>
    <ul class="grid gap-4 py-4 md:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4">
      <AppointmentCard :show-user="true" :appointment-card-type="appointment" :controls="true" :key="index"
        v-for="(appointment, index) in finalAppoinments" />
    </ul>
  </section>
  <ForbiddenAlert v-else />

</template>