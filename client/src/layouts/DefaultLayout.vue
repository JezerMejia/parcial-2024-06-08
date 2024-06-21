<script setup lang="ts">
import VueFeather from "vue-feather";
import ToastAlert from "../components/ToastAlert.vue";
import { useToast } from "@/stores/toast";
import { useUser } from "@/stores/user";

const { toasts } = useToast();
const currentUser = useUser();

</script>

<template>
  <main class="flex min-h-dvh flex-col gap-4 px-4">
    <header class="sticky top-4 mt-4 flex items-center justify-between rounded-md bg-white p-2 shadow-header">
      <a href="/">
        <img src="/identity/logo.svg" class="h-12" />
      </a>
      <nav class="flex gap-1">
        <div class=" pointer-events-none flex items-center gap-1 font-medium text-blue-400">
          <div class="grid size-10 place-items-center rounded-full border border-blue-400 bg-blue-200">
            <VueFeather class="size-6" type="user" />
          </div>
          <div>
            <span class="text-sm leading-none">{{ currentUser.user?.roles.join(",") }}</span>
            <p class="leading-none">{{ currentUser.user?.username }}</p>
          </div>
        </div>
        <a href="/auth/logout"
          class="flex h-full items-center gap-1 rounded-lg p-4 text-blue-400 transition-all hover:rounded-xl hover:bg-shades-100 active:scale-95">
          <VueFeather class="size-4" type="log-out" />
        </a>
      </nav>
    </header>
    <router-view />
    <ul class="absolute bottom-4 right-4 z-10 w-full max-w-lg">
      <ToastAlert v-for="toast in toasts" :toast="toast" :key="toast.id" />
    </ul>
  </main>
</template>
