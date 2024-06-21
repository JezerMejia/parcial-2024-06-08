import type Appointment from "@/types/Appointment";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";
import type ApproveAppointment from "@/types/ApproveAppointment";

export async function requestAppointment(appointment: Appointment) {
  return useAuthenticatedFetch("/appointment/request")
    .json<GeneralResponse<string>>()
    .post(appointment);
}

export async function rejectAppointment(appointmentUUID: string) {
  return useAuthenticatedFetch("/appointment/reject")
    .json<GeneralResponse<string>>()
    .post(appointmentUUID, "json"); // Sí, json...
}

export async function approveAppointment(approveAppointment: ApproveAppointment) {
  return useAuthenticatedFetch("/appointment/approve")
    .json<GeneralResponse<string>>()
    .post(approveAppointment);
}

export async function getOwnAppointments() {
  return useAuthenticatedFetch("/appointment/own")
    .json<GeneralResponse<Appointment[]>>()
}
