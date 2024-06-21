<script setup lang="ts">
import VueFeather from "vue-feather";
import ToastAlert from "../components/ToastAlert.vue";
import { useToast } from "@/stores/toast";

const { toasts } = useToast();
</script>

<template>
  <main class="flex min-h-dvh flex-col gap-4 px-4">
    <header class="mt-4 flex items-center justify-between rounded-md bg-white p-2 shadow-header">
      <a href="/">
        <img src="/identity/logo.svg" class="h-12" />
      </a>
      <a
        href="/auth/logout"
        class="flex h-full items-center gap-1 rounded-lg p-4 text-blue-400 transition-all hover:rounded-xl hover:bg-shades-100 active:scale-95"
      >
        <VueFeather type="log-out" />
        <span>Cerrar Sesión</span>
      </a>
    </header>
    <router-view />
    <ul class="absolute bottom-4 right-4 z-10 w-full max-w-lg">
      <ToastAlert v-for="toast in toasts" :toast="toast" :key="toast.id" />
    </ul>
  </main>
</template>
