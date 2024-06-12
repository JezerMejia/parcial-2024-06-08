package ni.factorizacion.parcial20240608.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import ni.factorizacion.parcial20240608.domain.entities.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class PrescriptionAppointmentDto {
    private String patientName;
    private PrescriptionDto prescription;

    @Data
    @AllArgsConstructor
    public static class PrescriptionDto {
        private List<AppointmentSummaryDto> appointments;

        public static PrescriptionDto from(List<Appointment> appointments) {
            List<AppointmentSummaryDto> appointmentSummaries = appointments.stream()
                    .map(AppointmentSummaryDto::from)
                    .collect(Collectors.toList());
            return new PrescriptionDto(appointmentSummaries);
        }
    }

    @Data
    @AllArgsConstructor
    public static class AppointmentSummaryDto {
        private String reason;
        private LocalDateTime startDate;
        private LocalDateTime endDate;

        public static AppointmentSummaryDto from(Appointment appointment) {
            return new AppointmentSummaryDto(
                    appointment.getReason(),
                    appointment.getStartDate(),
                    appointment.getEndDate()
            );
        }
    }

    public static PrescriptionAppointmentDto from(User patient, List<Appointment> appointments) {
        PrescriptionDto prescriptionDto = PrescriptionDto.from(appointments);
        return new PrescriptionAppointmentDto(patient.getUsername(), prescriptionDto);
    }
}
