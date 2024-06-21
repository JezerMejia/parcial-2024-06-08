package ni.factorizacion.parcial20240608.domain.dtos.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SaveAppointmentDto {
    @NotEmpty
    private String reason;
    @NotNull
    private LocalDate date;
}
