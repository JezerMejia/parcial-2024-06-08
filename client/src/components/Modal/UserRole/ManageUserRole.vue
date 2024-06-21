<script setup lang="ts">
import HeaderModal from "../HeaderModal.vue";
import Modal from "../ModalComponent.vue";
import VueFeather from "vue-feather";
import type User from "@/types/User";
import InputForm from "@/components/Forms/InputForm.vue";
import { toggleRole } from "@/composables/useConfig";
import type UserWithRole from "@/types/UserWithRole";
import { getUserRoles } from "@/composables/useUser";
import { ref } from "vue";
import { useToast } from "@/stores/toast";
import type Toast from "@/types/Toast";
import { ToastType } from "@/types/Toast";

const modal = ref<InstanceType<typeof Modal>>();

const props = defineProps<{ user?: User }>();
const currentRoles = ref<string[] | undefined>();
const { addToast } = useToast();

defineExpose({
  close: () => {
    modal.value?.close();
  },
  show: () => {
    currentRoles.value = undefined;
    modal.value?.show();
  },
});

const ALL_ROLES = ["DOCT", "RECP"];

async function handleRoleToggleClick(role: string) {
  const userWithRole: UserWithRole = {
    identifier: props.user?.username || "",
    role: role,
  };

  const response = (await toggleRole(userWithRole)).data.value;

  if (!response?.ok) {
    const toast: Toast = {
      message: response?.message || "Error inesperado, intente más tarde",
      type: ToastType.ERROR,
    };

    addToast(toast);
    return;
  }

  const roles = (await getUserRoles(props.user?.username || "")).data.value;
  currentRoles.value = (roles?.data as unknown as string[]) ?? undefined;
}

const ROLE_NAMES = {
  DOCT: "Médico",
  RECP: "Asistente",
};

const alreadyFetchedRoles = (role: string): boolean => {
  return currentRoles.value
    ? currentRoles.value.includes(role)
    : props.user?.roles.includes(role) || false;
};
</script>

<template>
  <Modal ref="modal">
    <HeaderModal icon="grid" title="Asignar Roles" />
    <form novalidate @submit.prevent="">
      <div class="flex flex-col gap-2.5 p-4">
        <InputForm
          title="Nombre de Usuario"
          :disabled="true"
          name="username"
          :value="props.user?.username"
        />
        <InputForm title="Correo" :disabled="true" name="email" :value="props.user?.email" />
        <div class="flex flex-col gap-1">
          <label class="font-medium">Roles para Alternar</label>
          <ul class="mt-2 grid grid-cols-2 gap-4">
            <li
              class="flex flex-wrap justify-between gap-2 rounded-lg bg-gray-50 p-2"
              :key="index"
              v-for="(role, index) in ALL_ROLES"
            >
              <p class="text-xl">{{ ROLE_NAMES[role as keyof typeof ROLE_NAMES] }}</p>
              <button
                @click="
                  () => {
                    handleRoleToggleClick(role);
                  }
                "
                :class="
                  alreadyFetchedRoles(role)
                    ? 'bg-green-200 text-green-400 hover:bg-green-300'
                    : 'bg-red-200 text-red-400 hover:bg-red-300'
                "
                class="grid size-8 place-items-center rounded-lg transition-all hover:rounded-xl active:scale-95"
              >
                <VueFeather :type="alreadyFetchedRoles(role) ? 'check' : 'x'" />
              </button>
            </li>
          </ul>
        </div>
      </div>
      <div class="flex justify-end gap-2 p-2">
        <button
          type="reset"
          class="flex items-center gap-1 rounded-lg bg-red-200 px-4 py-2 text-red-400 transition-all hover:rounded-xl hover:bg-red-300 active:scale-95"
          @click="modal?.close()"
        >
          <VueFeather stroke-width="4" size="18" type="x" />
          <span>Cerrar</span>
        </button>
      </div>
    </form>
  </Modal>
</template>
