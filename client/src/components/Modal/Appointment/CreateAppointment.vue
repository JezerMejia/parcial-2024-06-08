<script setup lang="ts">
import { onMounted, ref, type Ref } from "vue";
import HeaderModal from "../HeaderModal.vue";
import Modal from "../ModalComponent.vue";
import FormInput from "@/components/FormInput.vue";
import VueFeather from "vue-feather";
import type Appointment from "@/types/Appointment";
import { requestAppointment } from "@/composables/useAppointment";
import type { ErrorMap } from "@/types/ErrorMap";
import { setValidationErrorForm, type FormInputType } from "@/utils/formValidation";

const modal = ref<InstanceType<typeof Modal>>();

const formData = ref<Appointment>({
  reason: "",
  date: new Date().toISOString().split("T")[0],
});

defineExpose({
  close: () => modal.value?.close(),
  show: () => modal.value?.show(),
});

const reasonInput = ref<FormInputType>();
const dateInput = ref<FormInputType>();
const inputMap = new Map<string, Ref<FormInputType | undefined>>();

onMounted(() => {
  inputMap.set(reasonInput.value!.props.name, reasonInput);
  inputMap.set(dateInput.value!.props.name, dateInput);
});

async function request(): Promise<boolean> {
  const { data, statusCode, isFetching } = await requestAppointment(formData.value);

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

  if (valid) {
    formData.value.reason = "";
    formData.value.date = new Date().toISOString().split("T")[0];
  }
}
</script>

<template>
  <Modal ref="modal">
    <form novalidate class="flex flex-col gap-0" @submit.prevent="handleSubmit">
      <HeaderModal icon="grid" title="Crear cita" />
      <div class="flex max-h-[80vh] flex-col gap-6 px-4 py-6">
        <FormInput
          ref="reasonInput"
          label="Razón"
          type="text"
          name="reason"
          v-model="formData.reason"
        />
        <FormInput ref="dateInput" label="Fecha" type="date" name="date" v-model="formData.date" />
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
