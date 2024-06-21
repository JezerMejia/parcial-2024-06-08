<script setup lang="ts">
import { onMounted, ref } from "vue";
import { getAllHistories } from "@/composables/usePantientRecord";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import VueFeather from "vue-feather";
import type Record from "@/types/PantientRecord";
import { useUser } from "@/stores/user";

const records = ref<Record[]>([]);

onMounted(async () => {
  await getRecord();

  setInterval(async () => {
    await getRecord();
  }, 30000);
});

async function getRecord() {
  const user = useUser();
  if (!user.user) {
    return;
  }
  const { data } = await getAllHistories(user.user.email);
  const record = data.value;

  if (!record || !record.ok) return;
  records.value = record.data ?? [];
  console.log(records.value);
}
</script>
<template>
  <section class="rounded-md bg-white p-4">
    <CurrentPageInfo title="Ver Historial" icon="grid">
      <div class="flex flex-row items-center justify-end gap-2">
        <button
          @click="getRecord"
          class="inline-flex items-center rounded-lg bg-green-200 p-2 text-center text-sm font-normal text-green-400 transition-all hover:rounded-xl hover:bg-green-300 active:scale-95"
        >
          <VueFeather type="loader" stroke-width="2.5" size="16"></VueFeather>
        </button>
      </div>
    </CurrentPageInfo>
    <table class="mt-6 w-full rounded-md border border-blue-200 text-left">
      <thead class="bg-blue-100 text-blue-400">
        <tr class="*:px-2 *:py-4">
          <th>
            <div class="flex items-center gap-1">
              <VueFeather class="my-auto size-4" type="user" />
              <span class="leading-none">Razon</span>
            </div>
          </th>
          <th>
            <div class="flex items-center gap-1">
              <VueFeather class="my-auto size-4" type="calendar" />
              <span class="leading-none">Fecha</span>
            </div>
          </th>
        </tr>
      </thead>
      <tbody class="font-medium text-blue-500">
        <tr
          v-for="(record, index) in records"
          :key="index"
          class="border-b border-b-blue-200 *:border-r *:border-r-blue-200 *:p-2"
        >
          <td>
            <span>{{ record.reason }}</span>
          </td>
          <td>
            <span>{{ record.dateTime }}</span>
          </td>
        </tr>
      </tbody>
    </table>
  </section>
</template>
