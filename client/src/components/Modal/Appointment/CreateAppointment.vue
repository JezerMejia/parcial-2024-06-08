<script setup lang="ts">
import { ref } from "vue";
import HeaderModal from "../HeaderModal.vue";
import Modal from "../ModalComponent.vue";
import FormInput from "@/components/FormInput.vue";
import VueFeather from "vue-feather";

const modal = ref<InstanceType<typeof Modal>>();

const formData = ref({
  reason: "",
  date: new Date().toISOString().split("T")[0],
});

defineExpose({
  close: () => modal.value?.close(),
  show: () => modal.value?.show(),
});

async function handleSubmit() {
  console.log(formData.value);
}
</script>

<template>
  <Modal ref="modal">
    <HeaderModal icon="grid" title="Crear cita" />

    <form novalidate class="flex flex-col gap-0" @submit.prevent="handleSubmit">
      <div class="flex flex-col gap-2.5 p-4">
        <FormInput
          ref="identifierInput"
          label="Razón"
          type="text"
          name="reason"
          v-model="formData.reason"
        />
        <FormInput
          ref="identifierInput"
          label="Fecha"
          type="date"
          name="date"
          v-model="formData.date"
        />
      </div>

      <div class="flex flex-row gap-2 self-end p-2">
        <button
          type="submit"
          class="flex items-center gap-1 rounded-lg bg-green-200 p-2 text-green-400"
        >
          <VueFeather stroke-width="4" size="18" type="check" />
          <span> Aceptar </span>
        </button>
        <button
          type="reset"
          class="flex items-center gap-1 rounded-lg bg-red-200 p-2 text-red-400"
          @click="modal?.close()"
        >
          <VueFeather stroke-width="4" size="18" type="x" />
          <span> Cancelar </span>
        </button>
      </div>
    </form>
  </Modal>
</template>
