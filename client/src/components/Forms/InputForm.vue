<script setup lang="ts">
import { defineEmits, defineProps, onMounted } from "vue";
import toSlug from "@/utils/toSlug";

type InputType =
  | "text"
  | "number"
  | "date"
  | "datetime-local"
  | "email"
  | "month"
  | "password"
  | "radio"
  | "range"
  | "search"
  | "tel"
  | "time"
  | "url"
  | "week";

interface Props {
  title?: string;
  likes?: number;
  placeholder?: string;
  type?: InputType;
  disabled?: boolean;
  name?: string;
  value?: string;
}

const props = defineProps<Props>();

const titleSlug = toSlug(props.title || "");
const emitValue = defineEmits(["update:value"]);

const handleChange = (event: Event) => {
  const inputElement = event.target as HTMLInputElement;
  emitValue("update:value", inputElement.value);
};

onMounted(() => {
  emitValue("update:value", props.value);
});
</script>

<template>
  <div class="flex flex-col gap-1">
    <label :for="titleSlug" class="font-medium text-blue-500">
      {{ props.title }}
    </label>
    <input
      :type="props.type"
      :id="titleSlug"
      :name="props.name"
      class="rounded-md bg-gray-100 p-4 text-blue-500 transition-all hover:bg-gray-200 disabled:opacity-40"
      :placeholder="props.placeholder"
      :disabled="props.disabled"
      :value="props.value"
      @input="handleChange"
    />
  </div>
</template>
