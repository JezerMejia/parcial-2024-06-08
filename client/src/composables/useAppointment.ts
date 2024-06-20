import type Appointment from "@/types/Appointment";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";

export async function requestAppointment(appointment: Appointment) {
  return useAuthenticatedFetch("/appointment/request")
    .json<GeneralResponse<string>>()
    .post(appointment);
}
