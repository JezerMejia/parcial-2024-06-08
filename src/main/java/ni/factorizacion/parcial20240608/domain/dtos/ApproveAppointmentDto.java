package ni.factorizacion.parcial20240608.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
public class ApproveAppointmentDto {
    @NotNull
    private UUID appointmentId;
    @NotNull
    private LocalTime time;
    /**
     * En minutos
     */
    @NotNull
    private Long estimateDuration;
    @NotEmpty
    private List<MedicAppointmentDto> medics;
}
