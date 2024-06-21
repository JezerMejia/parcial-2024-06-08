<script setup lang="ts">
import { computed, onMounted, ref, type Ref } from "vue";
import HeaderModal from "../HeaderModal.vue";
import Modal from "../ModalComponent.vue";
import FormInput from "@/components/FormInput.vue";
import VueFeather from "vue-feather";
import { approveAppointment } from "@/composables/useAppointment";
import type { ErrorMap } from "@/types/ErrorMap";
import { setValidationErrorForm, type FormInputType } from "@/utils/formValidation";
import type ApproveAppointment from "@/types/ApproveAppointment";
import AppointmentMedicApprove from "./AppointmentMedicApprove.vue";
import FormSelect from "@/components/FormSelect.vue";
import { findMedics } from "@/composables/useDoctor";
import { findSpecialties } from "@/composables/useSpecialty";
import type MedicAppointment from "@/types/MedicAppointment";

const modal = ref<InstanceType<typeof Modal>>();

defineExpose({
  close: () => modal.value?.close(),
  show: () => modal.value?.show(),
});

const props = defineProps<{
  uuid: string;
}>();

const formData = ref<ApproveAppointment>({
  appointmentId: props.uuid,
  time: "09:00",
  medics: [],
  estimateDuration: 90,
});

const medicData = ref<ApproveAppointment["medics"][0]>({
  email: "",
  specialtyCode: "",
});

const inputMap = new Map<string, Ref<FormInputType | undefined>>();

const medicsObj = ref<{ [key: string]: string }>({});
const specialtiesObj = ref<{ [key: string]: string }>({});

const medicsFilteredObj = computed(() => {
  const medics = formData.value.medics;
  const entries = Object.entries(medicsObj.value).filter(([key]) =>
    medics.every((v) => v.email != key)
  );
  return Object.fromEntries(entries);
});
const specialtiesFilteredObj = computed(() => {
  const medics = formData.value.medics;
  const entries = Object.entries(specialtiesObj.value).filter(([key]) =>
    medics.every((v) => v.specialtyCode != key)
  );
  return Object.fromEntries(entries);
});

async function fetchMedics() {
  const { data } = await findMedics();
  const medics = data.value?.data;
  if (!medics) return;

  medicsObj.value = medics.reduce(
    (m, v) => {
      m[v.email] = v.username;
      return m;
    },
    {} as { [key: string]: string }
  );
}
async function fetchSpecialties() {
  const { data } = await findSpecialties();
  const specialties = data.value?.data;
  if (!specialties) return;

  specialtiesObj.value = specialties.reduce(
    (m, v) => {
      m[v.id] = v.name;
      return m;
    },
    {} as { [key: string]: string }
  );
}

onMounted(async () => {
  await Promise.allSettled([fetchMedics(), fetchSpecialties()]);
});

async function approve(): Promise<boolean> {
  const { data, statusCode, isFetching } = await approveAppointment(formData.value);

  if (!data.value) {
    return false;
  }

  if (statusCode.value == 400) {
    const errorMap = data.value?.data as unknown as ErrorMap;
    setValidationErrorForm(inputMap, errorMap);
  }

  return data.value?.ok;
}

async function handleAddMedic() {
  formData.value.medics.push(medicData.value);
  medicData.value = {
    email: "",
    specialtyCode: "",
  };
}
async function handleRemoveMedic(medic: MedicAppointment) {
  const index = formData.value.medics.findIndex(
    (v) => v.email == medic.email && v.specialtyCode == medic.specialtyCode
  );
  formData.value.medics.splice(index, 1);
}

async function handleSubmit() {
  const valid = await approve();

  if (valid) {
    modal.value?.close();
  }
}
</script>

<template>
  <Modal ref="modal">
    <HeaderModal icon="grid" title="Aprobar cita" />

    <form novalidate class="flex flex-col gap-0" @submit.prevent="handleSubmit">
      <div class="flex flex-col gap-2.5 p-4">
        <FormInput
          ref="timeInput"
          label="Hora de inicio"
          type="time"
          name="time"
          v-model="formData.time"
        />
        <FormInput
          ref="durationInput"
          label="Duración estimada (minutos)"
          type="number"
          name="estimateDuration"
          v-model.number="formData.estimateDuration"
        />

        <div class="grid grid-cols-2 gap-2.5 pt-4">
          <FormSelect
            label="Médico"
            name="medic"
            :entries="medicsFilteredObj"
            v-model="medicData.email"
          />
          <FormSelect
            label="Especialidad"
            name="specialty"
            :entries="specialtiesFilteredObj"
            v-model="medicData.specialtyCode"
          />
        </div>

        <button
          class="flex w-full items-center justify-center gap-1 rounded-lg bg-blue-200 p-2 text-blue-400"
          @click.prevent="handleAddMedic()"
        >
          <VueFeather stroke-width="4" size="18" type="plus" />
          <span> Agregar </span>
        </button>

        <div>
          <span>Médicos asignados</span>

          <div class="flex flex-col gap-2 pt-2 text-blue-400">
            <div
              class="flex grow flex-row gap-1"
              v-for="entry in formData.medics"
              :key="entry.email + entry.specialtyCode"
            >
              <AppointmentMedicApprove v-bind="entry" />
              <button class="p-1" @click="handleRemoveMedic(entry)">
                <VueFeather stroke-width="4" size="18" type="x" />
              </button>
            </div>
          </div>
        </div>
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
