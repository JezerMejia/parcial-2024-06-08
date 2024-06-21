<script setup lang="ts">
import { useRoute } from "vue-router";
import VueFeather from "vue-feather";
import type History from "@/types/History";
import { onMounted, ref } from "vue";
import { useAuthenticatedFetch } from "@/composables/useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";
import getFormattedDateTime from "@/utils/getFormattedDateTime";

const route = useRoute();
const par = route.params.username;
const historyList = ref<History[]>([]);

onMounted(async () => {
  const { data } = await useAuthenticatedFetch("/user/getHistoriesByPatient")
    .json<GeneralResponse<History[]>>()
    .post(par);
  const response = data.value;

  historyList.value = response?.data ?? [];

  console.log(response?.data);
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
            <span class="leading-none">Razón</span>
          </div>
        </th>
        <th>
          <div class="flex items-center justify-center gap-1">
            <VueFeather class="my-auto size-4" type="calendar" />
            <span class="leading-none">Fecha</span>
          </div>
        </th>
        <th class="w-40">
          <div class="flex items-center justify-center gap-1">
            <span class="leading-none">Gestionar</span>
          </div>
        </th>
      </tr>
    </thead>
    <tbody class="font-medium text-blue-500">
      <tr v-for="item in historyList" class="border-b border-b-blue-200 *:border-r *:border-r-blue-200 *:p-2">
        <td>
          <div class="flex justify-center">
            <span>{{ item.reason }}</span>
          </div>
        </td>
        <td>
          <div class="flex justify-center">
            <span>{{ formatDate(item.dateTime) }}</span>
          </div>
        </td>
        <td>
          <div class="flex items-center justify-center gap-[10px]">
            <button class="flex items-center justify-center size-[34px] bg-red-200 rounded-lg active:scale-95">
              <VueFeather class="size-[18px] text-red-300" type="trash-2" />
            </button>
            <button class="flex items-center justify-center size-[34px] bg-blue-200 rounded-lg active:scale-95">
              <VueFeather class="size-[18px] text-blue-400" type="edit-2" />
            </button>
          </div>
        </td>
      </tr>
    </tbody>
  </table>
</template>
