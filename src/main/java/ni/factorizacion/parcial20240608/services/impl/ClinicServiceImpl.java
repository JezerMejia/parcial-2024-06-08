package ni.factorizacion.parcial20240608.services.impl;
import ni.factorizacion.parcial20240608.domain.dtos.AppointmentMedicSimpleDto;
import ni.factorizacion.parcial20240608.domain.dtos.ScheduleSimpleDto;
import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.AppointmentMedicSpecialty;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.repositories.ClinicRepository;
import ni.factorizacion.parcial20240608.repositories.PrescriptionRepository;
import ni.factorizacion.parcial20240608.services.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicServiceImpl implements ClinicService {
    @Autowired
    private ClinicRepository repository;
    @Override
    public List<AppointmentMedicSimpleDto> getAllAppointmentByMedic() {
        return repository.findByMedic().stream()
                .map(AppointmentMedicSimpleDto::from)
                .collect(Collectors.toList());
    }
}