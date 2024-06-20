import type MedicAppointment from "./MedicAppointment";

export default interface ApproveAppointment {
  appointmentId: string;
  time: string;
  estimateDuration: number;
  medics: MedicAppointment[];
}
