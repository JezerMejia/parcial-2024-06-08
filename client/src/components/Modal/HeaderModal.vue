<script setup lang="ts">
import { defineProps, ref } from "vue";
import VueFeather from "vue-feather";

interface actionUI {
  icon: string;
  name: string;
}

const actions = {
  create: {
    icon: "file-plus",
    name: "Crear",
  },
  view: {
    icon: "eye",
    name: "Visualizar",
  },
  edit: {
    icon: "edit",
    name: "Modificar",
  },
  delete: {
    icon: "trash-2",
    name: "Eliminar",
  },
  add: {
    icon: "plus",
    name: "Agregar",
  },
};

interface Props {
  icon: "grid" | "user" | "user-minus" | "tablet" | "file-text" | "home";
  title: string;
  action?: "create" | "view" | "edit" | "delete" | "add";
}

const props = defineProps<Props>();
const currentAction = ref<actionUI>();

switch (props.action) {
  case "add":
    currentAction.value = actions.add;
    break;
  case "create":
    currentAction.value = actions.create;
    break;
  case "delete":
    currentAction.value = actions.delete;
    break;
  case "edit":
    currentAction.value = actions.edit;
    break;
  case "view":
    currentAction.value = actions.view;
    break;
}
</script>

<template>
  <header class="flex gap-2 bg-blue-100 p-2">
    <div class="flex items-center gap-1">
      <VueFeather stroke-width="1.5" size="24" :type="props.icon" />
      <p class="text-base font-medium">{{ props.title }}</p>
    </div>
    <div class="flex items-center gap-1" v-if="currentAction">
      <VueFeather stroke-width="1.5" size="20" :type="currentAction.icon" />
      <p class="font-medium">{{ currentAction.name }}</p>
    </div>
  </header>
</template>
