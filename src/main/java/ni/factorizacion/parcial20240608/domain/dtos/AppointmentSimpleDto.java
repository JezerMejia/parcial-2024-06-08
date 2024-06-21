package ni.factorizacion.parcial20240608.domain.dtos;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.AppointmentState;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@SuperBuilder
public class AppointmentSimpleDto {
    private UUID uuid;
    private String reason;
    private String requestDate;
    private String startDate;
    private String approxEndDate;
    private String endDate;
    private AppointmentState status;

    static public AppointmentSimpleDto from(Appointment appointment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return AppointmentSimpleDto.builder()
                .uuid(appointment.getUuid())
                .reason(appointment.getReason())
                .requestDate(appointment.getRequestDate().format(formatter))
                .startDate(appointment.getStartDate().format(formatter))
                .approxEndDate(appointment.getApproxEndDate() != null ? appointment.getApproxEndDate().format(formatter) : null)
                .endDate(appointment.getEndDate() != null ? appointment.getEndDate().format(formatter) : null)
                .status(appointment.getStatus())
                .build();
    }
}
