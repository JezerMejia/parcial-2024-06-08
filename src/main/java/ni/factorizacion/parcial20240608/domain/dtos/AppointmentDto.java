package ni.factorizacion.parcial20240608.domain.dtos;

import lombok.Data;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;

import java.util.List;

@Data
public class AppointmentDto {
    private String uuid;
    private String reason;
    private String requestDate;
    private String startDate;
    private String endDate;
    private String approxEndDate;
    private String status;
    private List<AppointmentPrescriptionDto> prescriptions;
    private List<SimpleMedicDto> medics;
}
