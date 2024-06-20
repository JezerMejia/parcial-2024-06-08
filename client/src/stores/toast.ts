import type Toast from "@/types/Toast";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useToast = defineStore("toast", () => {
    const toasts = ref<Toast[]>([]);

    const addToast = (toast: Toast) => {
        toast.id = Math.floor(Math.random() * 1000);
        toasts.value.push(toast);

        setTimeout(() => {
            toasts.value = toasts.value.filter(t => t.id !== toast.id);
        }, 1500);
    };

    return {
        toasts,
        addToast,
    };
});
