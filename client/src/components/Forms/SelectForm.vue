<script setup lang="ts">
import { defineEmits, defineProps, onMounted, ref } from "vue";
import type Option from "@/types/Option";

interface Props {
  textLabel: string,
  defaultOption: string;
  options: Option[];
  currentIndex?: number;
  disabled?: boolean;
  name: string;
}

const props = defineProps<Props>();

const currentOption = ref<Option | null>(null);

const emitValue = defineEmits(["update:value"]);

const handleChange = (event: Event) => {
  const selectElement = event.target as HTMLSelectElement;
  const selectedOptionIndex = selectElement.selectedIndex;
  currentOption.value = props.options[selectedOptionIndex - 1];
  emitValue("update:value", currentOption.value);
};

onMounted(() => {
  if (props.currentIndex !== undefined && props.options) {
    emitValue("update:value", props.options[props.currentIndex]);
  }
});
</script>

<template>
  <div class="flex flex-col gap-1">
    <label :for="props.name" class="font-medium text-blue-500">{{ textLabel }}</label>
    <select :name="props.name"
      class="rounded-md bg-gray-100 p-4 text-blue-500 transition-all hover:bg-gray-200 disabled:opacity-40"
      @change="handleChange" :disabled="props.disabled">
      <option disabled selected>{{ props.defaultOption }}</option>
      <option v-for="(option, index) in props.options" :key="index" :value="option.value"
        :selected="index === props.currentIndex">
        {{ option.text }}
      </option>
    </select>
  </div>
</template>
