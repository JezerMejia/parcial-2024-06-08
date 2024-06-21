<script setup lang="ts">
import { onMounted, onUnmounted, ref } from "vue";

const dialogRef = ref<HTMLDialogElement>();

function close() {
  if (dialogRef.value) {
    dialogRef.value.close();
  }
}
function show() {
  if (dialogRef.value) {
    dialogRef.value.show();
  }
}

function handleKeyup(ev: KeyboardEvent) {
  if (ev.key == "Escape") {
    close();
  }
}

defineExpose({
  show,
  close,
});

onMounted(() => {
  document.addEventListener("keyup", handleKeyup);
});
onUnmounted(() => {
  document.addEventListener("keydown", handleKeyup);
});
</script>

<template>
  <dialog
    ref="dialogRef"
    class="fixed left-0 top-0 z-10 hidden h-dvh w-dvw place-items-center bg-black/50 open:grid"
    @click="close()"
  >
    <div
      class="flex w-full max-w-xl flex-col gap-0 overflow-hidden rounded-lg border border-blue-300 bg-white"
      @click.stop=""
    >
      <slot></slot>
    </div>
  </dialog>
</template>
