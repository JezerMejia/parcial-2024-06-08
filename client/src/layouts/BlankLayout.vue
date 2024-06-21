<script setup lang="ts">
import VueFeather from "vue-feather";
import ToastAlert from "../components/ToastAlert.vue";
import { useToast } from "@/stores/toast";

const { toasts } = useToast();
</script>

<template>
  <main class="grid h-full flex-col place-items-center gap-4 px-4">
    <router-view />
    <ul class="absolute bottom-4 right-4 w-full max-w-lg">
      <ToastAlert v-for="(toast, index) in toasts" :toast="toast" :key="index" />
    </ul>
  </main>
</template>
