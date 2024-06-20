import type GeneralResponse from "@/types/GeneralResponse";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type SimpleSpecialty from "@/types/SimpleSpecialty";

export async function findSpecialties() {
  return useAuthenticatedFetch("/api/specialties").json<GeneralResponse<SimpleSpecialty[]>>().get();
}
