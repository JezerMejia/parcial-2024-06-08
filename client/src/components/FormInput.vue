<script setup lang="ts">
import { ref } from "vue";

const model = defineModel<string>();

const props = defineProps<{
  label?: string;
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
  <label class="flex flex-col gap-1">
    <span v-if="label" class="text-blue-400">{{ label }}</span>
    <input
      :type
      :name
      v-model="model"
      @input="handleInput"
      class="rounded bg-gray-100 p-2 font-light text-blue-500"
    />
    <span v-if="subLabel" class="text-xs text-blue-200">
      {{ subLabel }}
    </span>
    <span v-if="errorMessage" class="text-xs text-red-500" aria-live="polite">
      {{ errorMessage }}
    </span>
  </label>
</template>
