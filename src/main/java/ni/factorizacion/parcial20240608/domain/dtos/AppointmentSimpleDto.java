package ni.factorizacion.parcial20240608.domain.dtos;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.AppointmentState;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
public class AppointmentSimpleDto
{
    private UUID uuid;
    private String reason;
    private LocalDateTime requestDate;
    private LocalDateTime startDate;
    private LocalDateTime approxEndDate;
    private LocalDateTime endDate;
    private AppointmentState status;

    static public AppointmentSimpleDto from(Appointment appointment){
        return AppointmentSimpleDto.builder()
                .uuid(appointment.getUuid())
                .reason(appointment.getReason())
                .requestDate(appointment.getRequestDate())
                .startDate(appointment.getStartDate())
                .approxEndDate(appointment.getApproxEndDate())
                .endDate(appointment.getEndDate())
                .status(appointment.getStatus())
                .build();
    }
}
