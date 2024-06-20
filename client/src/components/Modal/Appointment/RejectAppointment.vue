<script setup lang="ts">
import { onMounted, ref, type Ref } from "vue";
import HeaderModal from "../HeaderModal.vue";
import Modal from "../ModalComponent.vue";
import VueFeather from "vue-feather";
import { rejectAppointment } from "@/composables/useAppointment";
import type { ErrorMap } from "@/types/ErrorMap";
import { setValidationErrorForm, type FormInputType } from "@/utils/formValidation";

const modal = ref<InstanceType<typeof Modal>>();

defineExpose({
  close: () => modal.value?.close(),
  show: () => modal.value?.show(),
});

const props = defineProps<{
  uuid: string;
}>();

const inputMap = new Map<string, Ref<FormInputType | undefined>>();

onMounted(() => {});

async function request(): Promise<boolean> {
  const { data, statusCode, isFetching } = await rejectAppointment(props.uuid);

  if (!data.value) {
    return false;
  }

  if (statusCode.value == 400) {
    const errorMap = data.value?.data as unknown as ErrorMap;
    setValidationErrorForm(inputMap, errorMap);
  }

  return data.value?.ok;
}

async function handleSubmit() {
  const valid = await request();

  modal.value?.close();
}
</script>

<template>
  <Modal ref="modal">
    <HeaderModal icon="user-minus" title="Finalizar cita" />

    <form novalidate class="flex flex-col gap-0" @submit.prevent="handleSubmit">
      <div class="flex flex-col gap-2.5 p-4">¿Desea rechazar la cita?</div>

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
