import { ExecutionState as ExcecutionStateType } from "@/types/ExecutionState";

export default interface History {
  status: ExcecutionStateType;
  username: string;
  startDate: Date;
  endDate: Date;
}
