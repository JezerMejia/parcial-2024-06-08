import type GeneralResponse from "@/types/GeneralResponse";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type UserWithRole from "@/types/UserWithRole";

export async function toggleRole(userWithRole: UserWithRole) {
    return useAuthenticatedFetch("/config/user-role")
      .json<GeneralResponse<string>>()
      .post(userWithRole);
  }
  