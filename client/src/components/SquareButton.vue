<script setup lang="ts">
import VueFeather from "vue-feather";

const props = defineProps<{
  title: string;
  href: string;
  icon: "folder" | "settings" | "eye" | "calendar";
}>();
</script>
<template>
  <a
    class="flex size-80 flex-col items-center justify-center gap-6 rounded-md bg-white p-4 text-blue-400 transition-all hover:bg-blue-400 hover:text-white hover:shadow-glow active:scale-95"
    :href="props.href"
  >
    <VueFeather :type="props.icon" class="size-[7.5rem]" />
    <span class="text-xl font-medium">{{ props.title }}</span>
  </a>
</template>
