package ni.factorizacion.parcial20240608.services.impl;

import ni.factorizacion.parcial20240608.domain.dtos.AppointmentSimpleDto;
import ni.factorizacion.parcial20240608.domain.dtos.HistorySimpleDto;
import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.History;
import ni.factorizacion.parcial20240608.repositories.AppointmentRepository;
import ni.factorizacion.parcial20240608.repositories.HistoryRepository;
import ni.factorizacion.parcial20240608.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository repository;

    public List<AppointmentSimpleDto> getAll() {
        return repository.findAll().stream().map(AppointmentSimpleDto::from).toList();
    }
    @Override
    public Optional<Appointment> findByUuid(UUID uuid){
        return repository.findByUuid(uuid);
    }


}
