<script setup lang="ts">
import SquareButton from '@/components/SquareButton.vue';
import { useUser } from '@/stores/user';
import { ref } from 'vue';
import VueFeather from "vue-feather";

const testingInput = ref<string>();

const handleInputUpdate = (value: string) => {
  testingInput.value = value;
}
const user = useUser()
const isDoctor = user.user?.roles.includes('DOCT')
const isAssistant = user.user?.roles.includes('RECP')
const isAdmin = user.user?.roles.includes('ADMN')
</script>
<template>
  <div class="flex flex-1 items-center justify-center gap-3">
    <SquareButton title="Ver mis citas" icon="folder" href="/auth/logout" />
    <SquareButton v-if="isDoctor || isAssistant" title="Ver horario medico" icon="calendar" href="/auth/logout" />
    <SquareButton v-if="isDoctor || isAssistant" title="Gestionar historial médico" icon="settings"
      href="/auth/logout" />
    <SquareButton v-if="isDoctor" title="Ver prescripciones" icon="eye" href="/auth/logout" />
    <SquareButton v-if="isAssistant" title="Gestionar especialidades" icon="settings" href="/auth/logout" />
    <SquareButton v-if="isAdmin" title="Asignar roles" icon="settings" href="/asignar-rol" />
  </div>
</template>
