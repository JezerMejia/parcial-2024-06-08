package ni.factorizacion.parcial20240608.domain.dtos.output;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.parcial20240608.domain.entities.Appointment;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@SuperBuilder
public class AppointmentDto {
    private String uuid;
    private String reason;
    private String requestDate;
    private String startDate;
    private String endDate;
    private String approxEndDate;
    private String status;
    private SimpleUserDto patient;
    private List<PrescriptionDto> prescriptions;
    private List<SimpleMedicDto> medics;

    static public AppointmentDto from(Appointment appointment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<SimpleMedicDto> medics = appointment.getAppointmentMedicSpecialty().stream().map(SimpleMedicDto::from).toList();

        return AppointmentDto.builder()
                .uuid(appointment.getUuid().toString())
                .reason(appointment.getReason())
                .requestDate(appointment.getRequestDate().format(formatter))
                .startDate(appointment.getStartDate().format(formatter))
                .approxEndDate(appointment.getApproxEndDate() != null ? appointment.getApproxEndDate().format(formatter) : null)
                .endDate(appointment.getEndDate() != null ? appointment.getEndDate().format(formatter) : null)
                .status(appointment.getStatus().toString())
                .patient(SimpleUserDto.from(appointment.getPatient()))
                .prescriptions(appointment.getPrescriptions().stream().map(PrescriptionDto::from).toList())
                .medics(medics)
                .build();
    }
}
