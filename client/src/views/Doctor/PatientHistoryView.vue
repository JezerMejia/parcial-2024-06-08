<script setup lang="ts">
import { useRoute } from 'vue-router';

const route = useRoute()
const par = route.params.username;
</script>
<template>
  <div>
    {{ par }}
  </div>
</template>
