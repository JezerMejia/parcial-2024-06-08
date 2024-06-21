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
    .post(appointmentUUID);
}

export async function approveAppointment(approveApp: ApproveAppointment) {
  return useAuthenticatedFetch("/appointment/approve")
    .json<GeneralResponse<string>>()
    .post(approveApp);
}

export async function startAppointment(appointmentUUID: string) {
  return useAuthenticatedFetch("/appointment/start")
    .json<GeneralResponse<string>>()
    .post(appointmentUUID);
}

export async function finishAppointment(appointmentUUID: string) {
  return useAuthenticatedFetch("/appointment/finish")
    .json<GeneralResponse<string>>()
    .post(appointmentUUID);
}

export async function getOwnAppointments() {
  return useAuthenticatedFetch("/appointment/own").json<GeneralResponse<Appointment[]>>();
}

export async function getAppointmentByMedic() {
  return useAuthenticatedFetch("/clinic/schedule").json<GeneralResponse<Appointment[]>>();
}
