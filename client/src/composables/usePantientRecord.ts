import type Patient from "@/types/PantientRecord";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";

export async function getAllHistories() {
  return useAuthenticatedFetch("/user/record?userIdentifier=juan.perez")
    .json<GeneralResponse<Patient[]>>()
}