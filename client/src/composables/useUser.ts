import { useAuthenticatedFetch } from "./useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";

export async function getAllUsers() {
  return useAuthenticatedFetch("/api/users").json<GeneralResponse<string>>();
}

export async function getUserRoles(emailOrUsername: string) {
  return useAuthenticatedFetch("/api/users/userRoles")
    .json<GeneralResponse<string>>()
    .post(emailOrUsername);
}
