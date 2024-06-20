<script setup lang="ts">
import { onMounted, ref } from 'vue';
import CurrentPageInfo from '@/components/CurrentPageInfo.vue';
import { getAllUsers, getUserRoles } from '@/composables/useUser';
import VueFeather from "vue-feather";
import type User from '@/types/User';
import ManageUserRole from "@/components/Modal/UserRole/ManageUserRole.vue"
import type ModalComponent from '@/components/Modal/ModalComponent.vue';

const usersWithRoles = ref<User[]>([]);
const selectedUser = ref<User | undefined>();
const manageModal = ref<InstanceType<typeof ModalComponent>>()

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

    usersWithRoles.value = fromDBUsers.value;
}


function handleModifyClick(user: User) {
    selectedUser.value = user;
    manageModal.value?.show();
}

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
                <tr class="*:p-2">
                    <th>Nombre de Usuario</th>
                    <th>Correo</th>
                    <th>Roles</th>
                    <th>Gestionar</th>
                </tr>
            </thead>
            <tbody class="font-medium text-blue-500">
                <tr v-for="(user, index) in usersWithRoles" :key="index"
                    class="border-b border-b-blue-200 *:border-r *:border-r-blue-200 *:p-2">
                    <td>{{ user.username }}</td>
                    <td>{{ user.email }}</td>
                    <td>{{ user.roles.join(", ") }}</td>
                    <td>
                        <button @click="() => { handleModifyClick(user) }"
                            class="grid aspect-square h-full min-h-9 w-auto place-content-center rounded-md bg-blue-200 text-blue-400 transition-all hover:bg-blue-300 active:scale-95">
                            <VueFeather class="size-[18px]" type="edit-2" />
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </section>
    <ManageUserRole :user="selectedUser" ref="manageModal" />
</template>