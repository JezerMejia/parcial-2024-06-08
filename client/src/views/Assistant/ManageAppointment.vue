<script setup lang="ts">
import CurrentPageInfo from '@/components/CurrentPageInfo.vue';
import { getOwnAppointments } from '@/composables/useAppointment';
import type Appointment from '@/types/Appointment';
import { onMounted, ref } from 'vue';
import AppointmentCard from '@/components/Cards/AppointmentCard.vue';
import VueFeather from "vue-feather";

const finalAppoinments = ref<Appointment[]>([])

onMounted(() => {
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
  <section class="rounded-md bg-white p-4">
    <CurrentPageInfo icon="grid" title="Gestionar Citas">
      <button @click="fetchAppointments"
        class="group grid aspect-square h-full min-h-9 w-auto place-content-center rounded-lg bg-green-200 text-green-400 transition-all hover:rounded-xl hover:bg-green-300 active:scale-95">
        <VueFeather class="size-[18px] transition-all group-active:rotate-[32deg]" type="loader" />
      </button>
    </CurrentPageInfo>
    <ul class="grid grid-cols-5 gap-4 py-4">
      <AppointmentCard :show-user="true" :appointment-card-type="appointment" :controls="true" :key="index"
        v-for="(appointment, index) in finalAppoinments" />
    </ul>
  </section>
</template>