<template>
  <section v-if="isAllowed" class="flex w-full flex-col gap-6 rounded-lg bg-white p-4">
    <CurrentPageInfo title="Ver prescripciones" icon="grid" />
    <ul class="grid grid-cols-5 gap-4">
      <PrescriptionCard
        :key="index"
        v-for="(item, index) in users"
        :patient="item"
        :asist="false"
      />
    </ul>
  </section>
  <ForbiddenAlert v-else />
</template>
<script setup lang="ts">
import PrescriptionCard from "@/components/Cards/PrescriptionCard.vue";
import { onMounted, ref } from "vue";
import type User from "@/types/User";
import { useAuthenticatedFetch } from "@/composables/useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import hasPermission from "@/utils/hasPermission";
import { RoleType } from "@/types/RoleType";
import ForbiddenAlert from "@/components/ForbiddenAlert.vue";

const users = ref<User[]>([]);
const isAllowed = hasPermission(RoleType.DOCT);

onMounted(async () => {
  if (!isAllowed) return;

  const { data } =
    await useAuthenticatedFetch("/api/users/getPatients").json<GeneralResponse<User[]>>();
  const response = data.value;
  users.value = response?.data ?? [];

  console.log(users.value);
});
</script>
