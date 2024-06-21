<script setup lang="ts">
import { useAuthenticatedFetch } from "@/composables/useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";
import type User from "@/types/User";
import { onMounted, ref } from "vue";
import VueFeather from "vue-feather";
const props = defineProps<{
  patient: User;
}>();

const nEntries = ref<number>(0)

onMounted(async () => {
  const { data } = await useAuthenticatedFetch("/user/getCountEntriesByPatient").json<GeneralResponse<number>>().post(props.patient.email);
  const response = data.value;
  nEntries.value = response?.data ?? 0;

  console.log("entries: " + response?.data);
})
</script>
<template>
  <div class="flex flex-col bg-blue-100 rounded-[4px] border border-blue-300">
    <div class="p-2">
      <h3 class="font-black">Paciente</h3>
      <div class="flex items-center gap-1">
        <div class="flex items-center size-5 justify-center rounded-full border border-blue-400">
          <VueFeather class="size-[10px] text-blue-400" type="user" />
        </div>
        <p class="font-light text-blue-400">{{ props.patient.username }}</p>
      </div>
      <p class="font-medium"> {{ nEntries }} entradas</p>
    </div>
    <div class="flex justify-end p-2 bg-white w-full rounded-b-[4px]">
      <a href="#" class="size-[34px] bg-blue-200 flex items-center justify-center rounded-lg active:scale-95">
        <VueFeather type="eye" class="size-[18px] font-black" />
      </a>
    </div>
  </div>
</template>
