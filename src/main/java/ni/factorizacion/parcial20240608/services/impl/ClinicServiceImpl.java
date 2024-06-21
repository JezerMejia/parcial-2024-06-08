package ni.factorizacion.parcial20240608.services.impl;

import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.repositories.ClinicRepository;
import ni.factorizacion.parcial20240608.services.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicServiceImpl implements ClinicService {
    @Autowired
    private ClinicRepository repository;

    @Override
    public List<Appointment> findByMedic(User medic) {
        return repository.findByMedic(medic);
    }
}