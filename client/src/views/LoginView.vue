<script setup lang="ts">
import { ref } from "vue";
import {
  useAuthenticatedFetch,
  useBaseFetch,
} from "../composables/useBaseFetch";
import GeneralResponse from "../types/GeneralResponse";
import { useUser } from "../stores/user";
import { useAuth } from "../stores/auth";
import { useRouter } from "vue-router";

enum Message {
  EMPTY = "",
  LOADING_LOGIN = "Iniciando sesión...",
  LOADING_USER = "Cargando datos de usuario...",
  NO_USER = "No existe un usuario para esta cuenta",
  ERROR_AUTH = "Inicio de sesión incorrecto",
  ERROR_USER = "No se pudo obtener los datos de este usuario, por favor intente más tarde",
  ERROR = "Ocurrió un error en el sistema, por favor intente más tarde",
}
const message = ref<Message>(Message.EMPTY);

const formData = ref({
  identifier: "",
  password: "",
});

const auth = useAuth();
const user = useUser();
const router = useRouter();

async function doLogin() {
  message.value = Message.LOADING_LOGIN;
  const { data, statusCode } = await useBaseFetch("/auth/login")
    .json<GeneralResponse<string>>()
    .post(formData.value);

  if (!data.value) {
    message.value = Message.ERROR;
    return;
  }

  if (statusCode.value == 404) {
    message.value = Message.NO_USER;
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
  message.value = Message.LOADING_LOGIN;

  const { data } = await useAuthenticatedFetch("/auth/self").json<
    GeneralResponse<string>
  >();
  if (data.value == null) {
    message.value = Message.ERROR_USER;
    return;
  }
  console.log(data.value);
  user.setUser(data.value.data);
}

async function handleSubmit() {
  await doLogin();
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
    <form
      @submit.prevent="handleSubmit"
      autocomplete="on"
      class="flex flex-col"
    >
      <input type="text" v-model="formData.identifier" />
      <input type="password" v-model="formData.password" />

      <button type="submit">Iniciar sesión</button>
    </form>

    <span class="">{{ message }}</span>
  </main>
</template>
