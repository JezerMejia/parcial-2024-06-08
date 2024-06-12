package ni.factorizacion.parcial20240608.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.AppointmentMedicSpecialty;
import ni.factorizacion.parcial20240608.domain.entities.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ScheduleSimpleDto {
    private String medicName;
    private List<AppointmentSummaryDto> appointments;

    @Data
    @AllArgsConstructor
    public static class AppointmentSummaryDto {
        private String reason;
        private String patientName;
        private LocalDateTime startDate;
        private LocalDateTime endDate;

        public static AppointmentSummaryDto from(Appointment appointment) {
            return new AppointmentSummaryDto(
                    appointment.getReason(),
                    appointment.getPatient().getUsername(),
                    appointment.getStartDate(),
                    appointment.getEndDate()
            );
        }
    }

    public static ScheduleSimpleDto from(User medic, List<Appointment> appointments) {
        List<AppointmentSummaryDto> appointmentSummaries = appointments.stream()
                .map(AppointmentSummaryDto::from)
                .collect(Collectors.toList());
        return new ScheduleSimpleDto(medic.getUsername(), appointmentSummaries);
    }
}
