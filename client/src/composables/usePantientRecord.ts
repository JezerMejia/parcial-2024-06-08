import type Patient from "@/types/PantientRecord";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";
import { useUser } from "@/stores/user";

export async function getAllHistories(email: string) {
  return useAuthenticatedFetch("/user/record?userIdentifier=" + email).json<
    GeneralResponse<Patient[]>
  >();
}

