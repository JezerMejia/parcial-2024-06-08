<script setup lang="ts">
import { onMounted, ref, type Ref } from "vue";
import { useAuthenticatedFetch, useBaseFetch } from "@/composables/useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";
import type User from "@/types/User";
import { useUser } from "@/stores/user";
import { useAuth } from "@/stores/auth";
import { useRouter } from "vue-router";
import type { ErrorMap } from "@/types/ErrorMap";
import FormInput from "@/components/FormInput.vue";
import { ValidationError } from "@/types/ValidationError";
import { setValidationErrorForm, type FormInputType } from "@/utils/formValidation";

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

const usernameInput = ref<FormInputType>();
const emailInput = ref<FormInputType>();
const passwordInput = ref<FormInputType>();
const confirmPasswordInput = ref<FormInputType>();
const inputMap = new Map<string, Ref<FormInputType | undefined>>();

onMounted(() => {
  inputMap.set(usernameInput.value!.props.name, usernameInput);
  inputMap.set(emailInput.value!.props.name, emailInput);
  inputMap.set(passwordInput.value!.props.name, passwordInput);
  inputMap.set(confirmPasswordInput.value!.props.name, confirmPasswordInput);
});

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
    setValidationErrorForm(inputMap, errorMap);
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
    const errorMap: ErrorMap = { confirmPassword: ["CONFIRM_PASSWORD"] };
    setValidationErrorForm(inputMap, errorMap);
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
function handleCancel() {
  router.push("/login");
}

</script>

<template>
  <main class="flex size-[570px] flex-col items-center justify-center gap-10 bg-white">
    <h1 class="text-4xl font-bold text-blue-400">Registra tu cuenta</h1>
    <form @submit.prevent="handleSubmit" autocomplete="on" class="flex flex-col gap-2" novalidate>
      <FormInput
        class="text-blue-400"
        ref="usernameInput"
        label="Nombre de usuario:"
        type="text"
        name="username"
        v-model="formData.username"
      />

      <FormInput
        class="text-blue-400"
        ref="emailInput"
        label="Correo electrónico: "
        type="email"
        name="email"
        v-model="formData.email"
      />

      <FormInput
        class="text-blue-400"
        ref="passwordInput"
        label="Contraseña: "
        type="password"
        name="password"
        sub-label="La contraseña debe empezar con números y terminar con una letra"
        v-model="formData.password"
      />

      <FormInput
        class="text-blue-400"
        ref="confirmPasswordInput"
        label="Confirmar contraseña: "
        type="password"
        name="confirmPassword"
        v-model="formData.confirmPassword"
      />

      <span v-if="message != Message.EMPTY">{{ message }}</span>
      
      <div class="flex justify-between gap-5">
        <button  type="button" @click="handleCancel" class="mt-3 flex w-full items-center justify-center gap-1 rounded-lg bg-red-200 p-2 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl active:scale-95">Cancelar</button>
        <button type="submit" class="mt-3 flex w-full items-center justify-center rounded-lg bg-blue-200 p-2 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl active:scale-95">
          <VueFeather type="log-out" stroke-width="2.5" size="16"/>
          <span>Ingresar</span>
        </button>
      </div>
    </form>
  </main>
</template>
