import type GeneralResponse from "@/types/GeneralResponse";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type SimpleUser from "@/types/SimpleUser";

export async function findMedics() {
  return useAuthenticatedFetch("/api/medics").json<GeneralResponse<SimpleUser[]>>().get();
}
