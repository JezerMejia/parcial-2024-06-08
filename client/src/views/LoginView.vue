<script setup lang="ts">
import { onMounted, ref, type Ref } from "vue";
import { useAuthenticatedFetch, useBaseFetch } from "@/composables/useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";
import type User from "@/types/User";
import { useUser } from "@/stores/user";
import { useAuth } from "@/stores/auth";
import { useRouter } from "vue-router";
import VueFeather from "vue-feather";
import type { ErrorMap } from "@/types/ErrorMap";
import { setValidationErrorForm, type FormInputType } from "@/utils/formValidation";
import FormInput from "@/components/FormInput.vue";
import InputForm from '@/components/Forms/InputForm.vue';


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

const identifierInput = ref<FormInputType>();
const passwordInput = ref<FormInputType>();
const inputMap = new Map<string, Ref<FormInputType | undefined>>();

onMounted(() => {
  inputMap.set(identifierInput.value!.props.name, identifierInput);
  inputMap.set(passwordInput.value!.props.name, passwordInput);
});

async function doLogin() {
  message.value = Message.LOADING_LOGIN;
  const { data, statusCode } = await useBaseFetch("/auth/login")
    .json<GeneralResponse<string>>()
    .post(formData.value);

  if (!data.value) {
    message.value = Message.ERROR;
    return;
  }

  if (statusCode.value == 400) {
    message.value = Message.EMPTY;
    const errorMap = data.value.data as unknown as ErrorMap;
    setValidationErrorForm(inputMap, errorMap);
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
  <main class="flex flex-col items-center justify-center gap-10 rounded-lg bg-white px-4 py-6">
    <div class="flex flex-col items-center justify-center">
      <img src="/identity/logo.svg" alt="Logo" />
      <h1  class="text-base font-bold text-blue-400">YA MERITO</h1>
      <h2  class="text-2xl font-bold text-blue-400">Inicia Sesion</h2>
    </div>
    <form @submit.prevent="handleSubmit" autocomplete="on" class="flex w-[500px] flex-col">
        
      <FormInput
        class="text-blue-400"
        ref="identifierInput"
        label="Identificador"
        type="text"
        name="identifier"
        v-model="formData.identifier"
      />

      <FormInput
        class="text-blue-400"
        ref="passwordInput"
        label="Contraseña"
        type="password"
        name="password"
        v-model="formData.password"
      />

      <span v-if="message != Message.EMPTY">{{ message }}</span>

      <button type="submit" class="mt-3 inline-flex items-center justify-center rounded-lg bg-blue-200 p-2.5 text-center text-sm font-medium text-blue-400 transition-all hover:rounded-xl hover:bg-blue-300 active:scale-95">
        <VueFeather type="log-out" stroke-width="2.5" size="16"/>
        <span>Ingresar</span>
      </button>
    </form>
  </main>
</template>
