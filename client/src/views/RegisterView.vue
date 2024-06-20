<script setup lang="ts">
import { ref } from "vue";
import { useAuthenticatedFetch, useBaseFetch } from "@/composables/useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";
import type User from "@/types/User";
import { useUser } from "@/stores/user";
import { useAuth } from "@/stores/auth";
import { useRouter } from "vue-router";
import type { ErrorMap } from "@/types/ErrorMap";

enum Message {
  EMPTY = "",
  LOADING_REGISTER = "Registrando usuario...",
  LOADING_USER = "Cargando datos de usuario...",
  USERNAME_USED = "Este nombre de usuario ya está en uso",
  EMAIL_USED = "Este correo electrónico ya está en uso",
  ERROR_AUTH = "Autenticación incorrecta",
  ERROR_USER = "No se pudo obtener los datos de este usuario, por favor intente más tarde",
  ERROR = "Ocurrió un error en el sistema, por favor intente más tarde",
}
const message = ref<Message>(Message.EMPTY);

const formData = ref({
  username: "",
  email: "",
  password: "",
  confirmPassword: "",
});

const auth = useAuth();
const user = useUser();
const router = useRouter();

async function doRegister() {
  message.value = Message.LOADING_REGISTER;
  const { data, statusCode } = await useBaseFetch("/auth/register")
    .json<GeneralResponse<string>>()
    .post(formData.value);

  if (!data.value) {
    message.value = Message.ERROR;
    return;
  }

  if (statusCode.value == 400) {
    message.value = Message.EMPTY;
    const errorMap = data.value.data as unknown as ErrorMap;
    console.log(errorMap);
    // TODO: Asignar errores a cada campo
    return;
  }

  if (statusCode.value == 409) {
    if (data.value.message == "Email already used") {
      message.value = Message.EMAIL_USED;
    } else {
      message.value = Message.USERNAME_USED;
    }
    return;
  }
  if (statusCode.value == 401) {
    message.value = Message.ERROR_AUTH;
    return;
  }
  console.log(data.value);
  if (!data.value.ok || typeof data.value.data !== "string") {
    message.value = Message.ERROR;
    return;
  }
  auth.setToken(data.value.data);
}

async function doUserData() {
  message.value = Message.LOADING_REGISTER;

  const { data } = await useAuthenticatedFetch("/auth/self").json<GeneralResponse<User>>();
  if (data.value == null) {
    message.value = Message.ERROR_USER;
    return;
  }
  if (typeof data.value.data !== "object") {
    message.value = Message.ERROR;
    return;
  }
  user.setUser(data.value.data);
}

function validateForm(): boolean {
  if (formData.value.password != formData.value.confirmPassword) {
    return false;
  }
  return true;
}

async function handleSubmit() {
  if (!validateForm()) {
    return;
  }

  await doRegister();
  if (auth.token != null) {
    await doUserData();
  }
  if (user.user != null) {
    router.push("/");
  }
}
</script>

<template>
  <main class="flex size-full flex-col items-center justify-center gap-24">
    <form @submit.prevent="handleSubmit" autocomplete="on" class="flex flex-col">
      <input type="text" v-model="formData.username" />
      <input type="email" v-model="formData.email" />
      <input type="password" v-model="formData.password" />
      <input type="password" v-model="formData.confirmPassword" />

      <button type="submit">Registrarse</button>
    </form>

    <span v-if="message != Message.EMPTY">{{ message }}</span>
  </main>
</template>
