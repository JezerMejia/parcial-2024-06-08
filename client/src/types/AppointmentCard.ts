import {ExecutionState as ExcecutionStateType } from "@/types/ExecutionState";

export default interface Appointment{
    status: ExcecutionStateType;
    startDate: Date;
    endDate: Date;
}