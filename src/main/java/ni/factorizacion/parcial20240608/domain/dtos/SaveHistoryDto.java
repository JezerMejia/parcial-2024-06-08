package ni.factorizacion.parcial20240608.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SaveHistoryDto {
    @NotNull
    private LocalDateTime dateTime;
    @NotEmpty
    private String reason;
}
