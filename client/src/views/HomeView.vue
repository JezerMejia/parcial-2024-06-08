<script setup lang="ts">
import DateTimeForm from '@/components/Forms/DateTimeForm.vue';
import InputForm from '@/components/Forms/InputForm.vue';
import { ref } from 'vue';

const testingInput = ref<string>()

const handleInputUpdate = (value: string) => {
  testingInput.value = value;
}

</script>
<template>
  <h1 class="text-4xl">Hola</h1>
  <div class="bg-white p-8">
    <DateTimeForm title="Testing" />
    <InputForm @update:value="handleInputUpdate" title="Testing" />
  </div>
</template>
