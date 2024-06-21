import { ExecutionState as ExcecutionStateType } from "@/types/ExecutionState";
import type Prescription from "./Prescription";
import type Medic from "./Medic";
import type SimpleUser from "./SimpleUser";

export default interface Appointment {
  uuid: string;
  reason: string;
  requestDate: string;
  startDate: string;
  endDate: string;
  approxEndDate: string;
  status: ExcecutionStateType;
    patient: SimpleUser;
  prescriptions: Prescription[];
  medics: Medic[];
}
