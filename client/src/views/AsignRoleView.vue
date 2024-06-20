<script setup lang="ts">
import { onMounted, ref, watch } from 'vue';
import CurrentPageInfo from '@/components/CurrentPageInfo.vue';
import { getAllUsers, getUserRoles } from '@/composables/useUser';
import VueFeather from "vue-feather";
import type User from '@/types/User';
import ManageUserRole from "@/components/Modal/UserRole/ManageUserRole.vue"
import type ModalComponent from '@/components/Modal/ModalComponent.vue';
import { useToast } from '@/stores/toast';

const usersWithRoles = ref<User[]>([]);
const selectedUser = ref<User | undefined>();
const manageModal = ref<InstanceType<typeof ModalComponent>>()
const {toasts} = useToast()

onMounted(async () => {
    await fetchUsers();

    setInterval(async ()=> {
        await fetchUsers();
    }, 30000)
});

async function fetchUsers() {
    const users = (await getAllUsers()).data.value;

    if (!users || !users.ok) return;

    const fromDBUsers = ref<User[]>([]);

    (users.data as any).map(async (user: any) => {
        const currentUserRoles = (await getUserRoles(user.username)).data.value;

        const newUser: User = {
            email: user.email,
            username: user.username,
            roles: (currentUserRoles?.data as unknown as string[])
        }
        fromDBUsers.value.push(newUser)
    })

    usersWithRoles.value = fromDBUsers.value.sort((a, b) => a.username.localeCompare(b.username));
}


function handleModifyClick(user: User) {
    selectedUser.value = user;
    manageModal.value?.show();
}

watch(toasts, (newToasts, oldToasts) => {
    console.log('Toasts han cambiado:', { oldToasts, newToasts });
  }, { deep: true });

</script>
<template>
    <section class="rounded-md bg-white p-4">
        <CurrentPageInfo title="Asignar Roles" icon="grid">
            <button
                @click="fetchUsers"
                class="group grid aspect-square h-full min-h-9 w-auto place-content-center rounded-md bg-green-200 text-green-400 transition-all hover:bg-green-300 active:scale-95">
                <VueFeather class="size-[18px] transition-all group-active:rotate-[32deg]" type="loader" />
            </button>
        </CurrentPageInfo>
        <table class="mt-6 w-full rounded-md border border-blue-200 text-left">
            <thead class="bg-blue-100 text-blue-400">
                <tr class="*:px-2 *:py-4">
                    <th>
                        <div class="flex items-center gap-1">
                            <VueFeather class="my-auto size-4" type="user" />
                            <span class="leading-none">Nombre de Usuario</span>
                        </div>
                    </th>
                    <th>
                        <div class="flex items-center gap-1">
                            <VueFeather class="my-auto size-4" type="mail" />
                            <span class="leading-none">Correo</span>
                        </div>
                    </th>
                    <th>
                        <div class="flex items-center gap-1">
                            <VueFeather class="my-auto size-4" type="shield" />
                            <span class="leading-none">Roles</span>
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
                <tr v-for="(user, index) in usersWithRoles" :key="index"
                    class="border-b border-b-blue-200 *:border-r *:border-r-blue-200 *:p-2">
                    <td>
                        <span>{{ user.username }}</span>
                    </td>
                    <td>
                        <span>{{ user.email }}</span>
                    </td>
                    <td>{{ user.roles.join(", ") }}</td>
                    <td>
                        <button @click="() => { handleModifyClick(user) }"
                            class="mx-auto grid aspect-square h-full min-h-9 w-auto place-content-center rounded-md bg-blue-200 text-blue-400 transition-all hover:bg-blue-300 active:scale-95">
                            <VueFeather class="size-[18px]" type="edit-2" />
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </section>
    <ManageUserRole :user="selectedUser" ref="manageModal" />
</template>