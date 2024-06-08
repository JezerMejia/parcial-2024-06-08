package ni.factorizacion.parcial20240608.domain.dtos;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.History;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
public class AppointmentSimpleDto {
    private UUID uuid;
    private LocalDateTime approx_end_date;
    private LocalDateTime end_date;
    private String reason;
    private LocalDateTime request_date;
    private LocalDateTime start_date;
    private UUID patient_UUID;

    static public AppointmentSimpleDto from(Appointment appointment){
        return AppointmentSimpleDto.builder()
                .uuid(appointment.getUuid())
                .approx_end_date(appointment.getApproxEndDate())
                .end_date(appointment.getEndDate())
                .reason(appointment.getReason())
                .request_date(appointment.getRequestDate())
                .start_date(appointment.getStartDate())
                .build();
    }
}
