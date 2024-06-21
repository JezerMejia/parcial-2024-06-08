<script setup lang="ts">
import { ref } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
import { finishAppointment } from "@/composables/useAppointment";

const modal = ref<typeof Modal>();

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});

const props = defineProps<{
  uuid: string;
}>();

async function request(): Promise<boolean> {
  const { data } = await finishAppointment(props.uuid);

  if (!data.value) {
    return false;
  }

  return data.value.ok;
}

async function handleFinishAppointment() {
  await request();

  modal.value?.close();
}
</script>

<template>
  <Modal ref="modal">
    <form class="overflow-hidden rounded-md bg-white">
      <HeaderModal title="Ver Medicos Asignados" icon="grid" />
      <div class="flex max-h-[80vh] w-full flex-col gap-4 p-4">
        <ul class="grid gap-4 min-[480px]:grid-cols-1">
          <p>¿Desea finalizar la Cita?</p>
        </ul>
      </div>

      <!-- Botones -->
      <div class="flex flex-row justify-end gap-2 p-2">
        <button
          @click="handleFinishAppointment()"
          type="button"
          class="inline-flex items-center rounded-lg bg-green-100 p-2 text-center text-sm font-normal text-green-400 transition-all hover:rounded-xl hover:bg-green-200 active:scale-95"
        >
          <VueFeather type="check" stroke-width="2.5" size="16"></VueFeather>
          <span>Aceptar</span>
        </button>
        <button
          @click="modal?.close()"
          type="button"
          class="inline-flex items-center rounded-lg bg-red-100 p-2 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-200 active:scale-95"
        >
          <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
          <span>Cancelar</span>
        </button>
      </div>
    </form>
  </Modal>
</template>
