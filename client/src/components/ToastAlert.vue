<script setup lang="ts">
import { defineProps } from "vue";
import VueFeather from "vue-feather";
import type Toast from "../types/Toast";
import { ToastType } from "../types/Toast";

const toastBackground: { [key in ToastType]: string } = {
  [ToastType.INFO]: "bg-blue-100",
  [ToastType.ERROR]: "bg-red-100",
  [ToastType.SUCCESS]: "bg-green-100",
  [ToastType.WARNING]: "bg-yellow-100",
};

const toastBackgroundDark: { [key in ToastType]: string } = {
  [ToastType.INFO]: "group-hover:bg-blue-200",
  [ToastType.ERROR]: "group-hover:bg-red-200",
  [ToastType.SUCCESS]: "group-hover:bg-green-200",
  [ToastType.WARNING]: "group-hover:bg-yellow-200",
};

const toastBorder: { [key in ToastType]: string } = {
  [ToastType.INFO]: "border-blue-300",
  [ToastType.ERROR]: "border-red-300",
  [ToastType.SUCCESS]: "border-green-300",
  [ToastType.WARNING]: "border-yellow-300",
};

const toastIcons: { [key in ToastType]: string } = {
  [ToastType.INFO]: "info",
  [ToastType.ERROR]: "x-circle",
  [ToastType.SUCCESS]: "check",
  [ToastType.WARNING]: "alert-triangle",
};

const toastColor: { [key in ToastType]: string } = {
  [ToastType.INFO]: "text-blue-400",
  [ToastType.ERROR]: "text-red-400",
  [ToastType.SUCCESS]: "text-green-400",
  [ToastType.WARNING]: "text-yellow-400",
};

const props = defineProps<{ toast: Toast }>();
</script>

<template>
  <li
    class="my-2 flex flex-row items-center justify-between gap-2 rounded-md border-2"
    :class="[toastBackground[props.toast.type], toastBorder[props.toast.type]]"
  >
    <div class="flex flex-row items-center p-2" :class="toastColor[props.toast.type]">
      <div class="flex size-10 shrink-0 items-center justify-center">
        <VueFeather :type="toastIcons[props.toast.type]" size="25" stroke-width="1.5"></VueFeather>
      </div>

      <p class="font-semibold">{{ props.toast.message }}</p>
    </div>

    <button
      class="group relative hidden flex-row items-center justify-center border-l-2 px-3 transition-all active:scale-90  "
      :class="[toastColor[props.toast.type], toastBorder[props.toast.type]]"
    >
      <div
        class="absolute left-1/2 top-1/2 size-8 -translate-x-1/2 -translate-y-1/2 rounded-full transition-colors"
        :class="toastBackgroundDark[props.toast.type]"
      ></div>
      <VueFeather
        type="x"
        size="16"
        stroke-width="3.5"
        class="z-10 ml-[1.5px] transition-colors"
      ></VueFeather>
    </button>
  </li>
</template>