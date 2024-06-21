package ni.factorizacion.parcial20240608.domain.dtos.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SavePrescriptionDto {
    @NotNull
    private UUID uuid_appointment;
    @NotNull
    private LocalDateTime endDate;
    @NotEmpty
    private String medicine;
    @NotEmpty
    private String dose;
}
