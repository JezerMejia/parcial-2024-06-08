package ni.factorizacion.parcial20240608.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import ni.factorizacion.parcial20240608.domain.entities.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionAppointmentDto {
    private String patient;
    private Prescription prescription;
    private Appointment appointment;
}
