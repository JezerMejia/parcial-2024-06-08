package ni.factorizacion.parcial20240608.services;

import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.User;

import java.util.List;

public interface ClinicService {
    List<Appointment> findByMedic(User medic);
}