<script setup lang="ts">
import { useRoute } from "vue-router";
import VueFeather from "vue-feather";
import { onMounted, ref } from "vue";
import getFormattedDateTime from "@/utils/getFormattedDateTime";
import type Appointment from "@/types/Appointment";
import type Prescription from "@/types/Prescription";
import { getPrescriptions } from "@/composables/usePrescription";

const route = useRoute();
const par = route.params.username;
const prescriptionsList = ref<Prescription[]>([]);

onMounted(async () => {
  const { data } = await getPrescriptions(par as string);
  prescriptionsList.value = data.value?.data ?? [];
});

function formatDate(value: string) {
  const date = new Date(value);
  return getFormattedDateTime(date);
}
</script>
<template>
  <table class="mt-6 w-full rounded-md border border-blue-200 text-left">
    <thead class="bg-blue-200 text-blue-400">
      <tr class="*:px-2 *:py-4">
        <th>
          <div class="flex items-center justify-center gap-1">
            <VueFeather class="my-auto size-4" type="book" />
            <span class="leading-none">Medicina</span>
          </div>
        </th>
        <th>
          <div class="flex items-center justify-center gap-1">
            <VueFeather class="my-auto size-4" type="heart" />
            <span class="leading-none">Dosis</span>
          </div>
        </th>
        <th>
          <div class="flex items-center justify-center gap-1">
            <VueFeather class="my-auto size-4" type="calendar" />
            <span class="leading-none">Termina</span>
          </div>
        </th>
      </tr>
    </thead>
    <tbody class="font-medium text-blue-500">
      <tr v-for="item in prescriptionsList" class="border-b border-b-blue-200 *:border-r *:border-r-blue-200 *:p-2">
        <td>
          <div class="flex justify-center">
            <span>{{ item.medicine }}</span>
          </div>
        </td>
        <td>
          <div class="flex justify-center">
            <span>{{ item.dose }}</span>
          </div>
        </td>
        <td>
          <div class="flex justify-center">
            <span>{{ formatDate(item.endDate) }}</span>
          </div>
        </td>
      </tr>
    </tbody>
  </table>
</template>
