<script setup lang="ts">
import { ref } from "vue";

const model = defineModel<string>();

const props = defineProps<{
  label?: string;
  class?: string;
  subLabel?: string;
  name: string;
  entries: { [key: string]: string } | undefined;
}>();

const errorMessage = ref<string | null>(null);

function setError(error: string | null) {
  errorMessage.value = error;
}

defineExpose({ setError, props });
</script>

<template>
  <label class="flex flex-col gap-1" :class="props.class">
    <span v-if="label">{{ label }}</span>
    <select
      :name
      v-model="model"
      class="appearance-none rounded-md bg-gray-100 p-4 text-blue-500 transition-all hover:bg-gray-200 disabled:opacity-40"
    >
      <option disabled value="" selected>Selecciona una opción</option>
      <option v-for="[key, value] in Object.entries(props.entries ?? {})" :key="key" :value="key">
        {{ value }}
      </option>
    </select>
    <span v-if="subLabel" class="font-medium text-blue-500">
      {{ subLabel }}
    </span>
    <span v-if="errorMessage" class="text-xs text-red-500" aria-live="polite">
      {{ errorMessage }}
    </span>
  </label>
</template>
