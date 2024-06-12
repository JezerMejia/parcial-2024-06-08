package ni.factorizacion.parcial20240608.services.impl;

import ni.factorizacion.parcial20240608.domain.dtos.PrescriptionAppointmentDto;
import ni.factorizacion.parcial20240608.domain.dtos.PrescriptionSimpleDto;
import ni.factorizacion.parcial20240608.domain.dtos.ScheduleSimpleDto;
import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.AppointmentMedicSpecialty;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.repositories.AppointmentRepository;
import ni.factorizacion.parcial20240608.repositories.ClinicRepository;
import ni.factorizacion.parcial20240608.repositories.PrecriptionRepository;
import ni.factorizacion.parcial20240608.services.ClinicService;
import ni.factorizacion.parcial20240608.types.ControlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClinicServiceImpl implements ClinicService {
    @Autowired
    private ClinicRepository repository;
    private PrecriptionRepository repositoryPre;

    @Override
    public List<ScheduleSimpleDto> getAllMedicsAppointments() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(AppointmentMedicSpecialty::getMedic))
                .entrySet().stream()
                .map(entry -> {
                    User medic = entry.getKey();
                    List<Appointment> appointments = entry.getValue().stream()
                            .map(AppointmentMedicSpecialty::getAppointment)
                            .collect(Collectors.toList());
                    return ScheduleSimpleDto.from(medic, appointments);
                })
                .toList();
    }
}
