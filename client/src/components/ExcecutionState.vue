<script setup lang="ts">
import { ExecutionState } from "@/types/ExecutionState"

const props = defineProps<{ state: ExecutionState }>()

const background: {[key in ExecutionState] : string } = {
    [ExecutionState.CANCELED]: "bg-red-200",
    [ExecutionState.REJECTED]: "bg-red-200",
    [ExecutionState.FINISHED]: "bg-green-200",
    [ExecutionState.IN_EXC]: "bg-yellow-200",
    [ExecutionState.PEND_APR]: "bg-blue-200",
    [ExecutionState.PEND_EXC]: "bg-blue-200",
}

const text: {[key in ExecutionState] : string } = {
    [ExecutionState.CANCELED]: "text-red-400",
    [ExecutionState.REJECTED]: "text-red-400",
    [ExecutionState.FINISHED]: "text-green-400",
    [ExecutionState.IN_EXC]: "text-yellow-400",
    [ExecutionState.PEND_APR]: "text-blue-400",
    [ExecutionState.PEND_EXC]: "text-blue-400",
}


</script>
<template>
    <div class="p-2 text-center font-bold" :class="[background[props.state], text[props.state]]">
        <span>{{ props.state }}</span>
    </div>
</template>