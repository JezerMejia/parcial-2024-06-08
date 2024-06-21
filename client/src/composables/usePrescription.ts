import type GeneralResponse from "@/types/GeneralResponse";
import type Prescription from "@/types/Prescription";
import { useAuthenticatedFetch } from "./useBaseFetch";

export async function getPrescriptions(identifier: string) {
  return useAuthenticatedFetch("/clinic/prescriptions/" + identifier).json<
    GeneralResponse<Prescription[]>
  >();
}
