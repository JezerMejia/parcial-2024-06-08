<script setup lang="ts">
import VueFeather from "vue-feather";
</script>

<template>
    <li class="flex flex-col bg-white">
        <!--Intro-->
        <div class="flex flex-col gap-1 bg-blue-100 p-2">
            <p class="font-bold">Cita Medica</p>
        </div>
        <!--Fechas-->
        <div class="flex flex-1 flex-row border-x p-4">
            <ul class="flex w-full flex-col justify-center gap-2 text-blue-400">
                <li class="flex items-center gap-2">
                    <VueFeather type="calendar" size="40" stroke="#01193F" stroke-width="1.5"></VueFeather>
                    <div class="flex flex-col">
                        <p class="font-semibold">Fecha de inicio</p>
                        <p class="font-semibold">12 de abril</p>
                    </div>
                </li>
                <li class="flex items-center gap-2">
                    <VueFeather type="calendar" size="40" stroke="#01193F" stroke-width="1.5"></VueFeather>
                    <div class="flex flex-col">
                        <p class="font-semibold">Fecha de fin</p>
                        <p class="font-semibold">12 de abril</p>
                    </div>
                </li>
            </ul>
        </div>
        <div class="flex flex-row justify-end gap-2 rounded-b-lg border p-2">
            <button type="button"
                class="inline-flex items-center rounded-lg bg-blue-200   p-2.5 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-300 active:scale-95">
                <VueFeather type="eye" stroke-width="3.5" size="16"></VueFeather>
            </button>
        </div>
    </li>
</template>