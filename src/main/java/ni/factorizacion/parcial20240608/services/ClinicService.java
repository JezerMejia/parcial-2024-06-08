package ni.factorizacion.parcial20240608.services;

import ni.factorizacion.parcial20240608.domain.dtos.*;

import java.util.List;

public interface ClinicService {
    List<AppointmentMedicSimpleDto> getAllAppointmentByMedic();

}