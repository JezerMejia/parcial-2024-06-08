import type GeneralResponse from "@/types/GeneralResponse";
import { useAuthenticatedFetch } from "./useBaseFetch";

export async function addHistoryEntry(userIdentifier: string, reason: string) {
  const params = new URLSearchParams();
  params.set("userIdentifier", userIdentifier);
  params.set("reason", reason);

  return useAuthenticatedFetch("/user/record?" + params.toString())
    .json<GeneralResponse<string>>()
    .post();
}
