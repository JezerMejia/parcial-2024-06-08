import { useUser } from "@/stores/user";
import { RoleType } from "@/types/RoleType";


export default function hasPermission(role: RoleType) {
    const props = useUser();
    return props.user?.roles.includes(RoleType[role]);
}