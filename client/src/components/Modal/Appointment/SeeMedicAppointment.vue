<script setup lang="ts">
import { ref } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
import type Medic from "@/types/Medic";

const modal = ref<typeof Modal>();
const props = defineProps<{ medics: Medic[] }>();

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <form class="overflow-hidden rounded-md bg-white">
      <HeaderModal title="Ver Medicos Asignados" icon="grid" />
      <div class="flex max-h-[80vh] w-full flex-col gap-4 p-4">
        <div>
          <ul class="grid gap-4 min-[480px]:grid-cols-1" v-if="props.medics.length > 0">
            <li class="flex flex-row gap-2" :key="index" v-for="(medic, index) in props.medics">
              <div class="flex-1 text-blue-500">
                <p class="text-sm font-semibold">Medico</p>
                <p class="text-base font-normal">
                  {{ medic.username }}
                </p>
              </div>
              <div class="flex-1 text-right text-blue-500">
                <p class="text-sm font-semibold">Especialidad</p>
                <p class="text-base font-normal">
                  {{ medic.specialty }}
                </p>
              </div>
            </li>
          </ul>
          <p v-else>No hay ningún médico asociado</p>
        </div>
      </div>

      <!-- Botones -->
      <div class="flex justify-end gap-2 p-2">
        <button
          @click="modal?.close()"
          type="button"
          class="inline-flex items-center rounded-lg bg-red-100 p-2 px-4 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-200 active:scale-95"
        >
          <span>Cerrar</span>
        </button>
      </div>
    </form>
  </Modal>
</template>
