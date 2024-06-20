<script setup lang="ts">
import VueFeather from "vue-feather";

const props = defineProps<{
  title: string;
  href: string;
  icon: "folder" | "settings" | "eye" | "calendar";
}>();
</script>
<template>
  <div>
    <a
      class="flex rounded-md flex-col items-center justify-center bg-white size-80 text-blue-400 hover:bg-blue-400 hover:text-white transition-all gap-6 p-4 active:scale-95 hover:shadow-glow"
      :href="props.href"
    >
      <VueFeather :type="props.icon" class="size-[7.5rem]" />
      <span class="font-medium text-xl">{{ props.title }}</span>
    </a>
  </div>
</template>
