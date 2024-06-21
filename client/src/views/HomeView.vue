<script setup lang="ts">
import SquareButton from "@/components/SquareButton.vue";
import { useUser } from "@/stores/user";
import { ref } from "vue";
import VueFeather from "vue-feather";

const testingInput = ref<string>();

const handleInputUpdate = (value: string) => {
  testingInput.value = value;
};
const user = useUser();
const isDoctor = user.user?.roles.includes("DOCT");
const isAssistant = user.user?.roles.includes("RECP");
const isAdmin = user.user?.roles.includes("ADMN");
</script>
<template>
  <div class="flex flex-1 items-center justify-center gap-3">
    <SquareButton title="Ver mis citas" icon="folder" href="/" />
    <SquareButton
      v-if="isDoctor || isAssistant"
      title="Ver horario medico"
      icon="calendar"
      href="/medico/historial"
    />
    <SquareButton
      v-if="isDoctor || isAssistant"
      title="Gestionar historial médico"
      icon="settings"
      href="/doctor/gestionHistorial"
    />
    <SquareButton v-if="isDoctor" title="Ver prescripciones" icon="eye" href="/" />
    <SquareButton v-if="isAssistant" title="Gestionar especialidades" icon="settings" href="/" />
    <SquareButton v-if="isAdmin" title="Asignar roles" icon="settings" href="/asignar-rol" />
  </div>
</template>
