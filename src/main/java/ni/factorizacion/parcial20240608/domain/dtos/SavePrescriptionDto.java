package ni.factorizacion.parcial20240608.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SavePrescriptionDto {
    @NotNull
    private LocalDateTime endDate;
    @NotEmpty
    private String medicine;
    @NotEmpty
    private String dose;
}
