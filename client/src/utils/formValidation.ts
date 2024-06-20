import type FormInput from "@/components/FormInput.vue";
import type { ErrorMap } from "@/types/ErrorMap";
import { ValidationError } from "@/types/ValidationError";
import type { Ref } from "vue";

export type FormInputType = InstanceType<typeof FormInput>;

export function setValidationErrorForm(
  formInputMap: Map<string, Ref<FormInputType | undefined>>,
  errorMap: ErrorMap
) {
  const validationErrors = Object.entries(ValidationError);

  for (const [field, errors] of Object.entries(errorMap)) {
    const input = formInputMap.get(field);
    if (!input?.value) continue;

    const errorMessages = errors
      .map((v) => validationErrors.find(([k]) => k == v)?.[1] ?? v)
      .sort();

    input.value.setError(errorMessages.join(", "));
  }
}
