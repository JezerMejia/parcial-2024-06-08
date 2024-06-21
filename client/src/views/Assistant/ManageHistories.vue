<script setup lang="ts">
import CurrentPageInfo from '@/components/CurrentPageInfo.vue';
import VueFeather from "vue-feather";
</script>
<template>
    <section class="rounded-md bg-white p-4">
        <CurrentPageInfo title="Asignar Roles" icon="grid">
            <div class="flex flex-row items-center justify-end gap-2">
                <button
                    class="inline-flex items-center rounded-lg bg-green-200 p-2 text-center text-sm font-normal text-green-400 transition-all hover:rounded-xl hover:bg-green-300 active:scale-95">
                    <VueFeather type="loader" stroke-width="2.5" size="16"></VueFeather>
                </button>
                <button
                    class="inline-flex items-center rounded-lg bg-blue-200 p-2 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-300 active:scale-95">
                    <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
                    <span>Nueva entrada</span>
                </button>
            </div>


        </CurrentPageInfo>
        <table class="mt-6 w-full rounded-md border border-blue-200 text-left">
            <thead class="bg-blue-100 text-blue-400">
                <tr class="*:px-2 *:py-4">
                    <th>
                        <div class="flex items-center gap-1">
                            <VueFeather class="my-auto size-4" type="user" />
                            <span class="leading-none">Nombre</span>
                        </div>
                    </th>
                    <th>
                        <div class="flex items-center gap-1">
                            <VueFeather class="my-auto size-4" type="calendar" />
                            <span class="leading-none">Fecha</span>
                        </div>
                    </th>
                    <th>
                        <div class="flex items-center gap-1">
                            <VueFeather class="my-auto size-4" type="sliders" />
                            <span class="leading-none">Gestionar</span>
                        </div>
                    </th>
                </tr>
            </thead>
            <tbody class="font-medium text-blue-500">
                <tr class="border-b border-b-blue-200 *:border-r *:border-r-blue-200 *:p-2">
                    <td>
                        <span>Pedro</span>
                    </td>
                    <td>
                        <span>Sanchez</span>
                    </td>
                    <td class="flex flex-row justify-center gap-2">
                        <button
                            class="inline-flex items-center rounded-lg bg-blue-200 p-2 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-300 active:scale-95">
                            <VueFeather type="edit-2" stroke-width="2.5" size="16"></VueFeather>
                        </button>
                        <button
                            class="inline-flex items-center rounded-lg bg-red-200 p-2 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-300 active:scale-95">
                            <VueFeather type="trash" stroke-width="2.5" size="16"></VueFeather>
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </section>
</template>