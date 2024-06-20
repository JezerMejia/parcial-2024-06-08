<script setup lang="ts">
import { ref } from "vue";

const model = defineModel<string | number>();

const props = defineProps<{
  label?: string;
  class?: string;
  subLabel?: string;
  type: string;
  name: string;
}>();

const errorMessage = ref<string | null>(null);

function setError(error: string | null) {
  errorMessage.value = error;
}

function handleInput() {
  errorMessage.value = "";
}

defineExpose({ setError, props });
</script>

<template>
  <label class="flex flex-col gap-1" :class="props.class">
    <span v-if="label">{{ label }}</span>
    <input
      :type
      :name
      v-model="model"
      @input="handleInput"
      class="rounded-md bg-gray-100 p-3 text-blue-500 transition-all hover:bg-gray-200 disabled:opacity-40"
    />
    <span v-if="subLabel" class="font-medium text-blue-500">
      {{ subLabel }}
    </span>
    <span v-if="errorMessage" class="text-xs text-red-500" aria-live="polite">
      {{ errorMessage }}
    </span>
  </label>
</template>
