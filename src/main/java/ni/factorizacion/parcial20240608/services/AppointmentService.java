package ni.factorizacion.parcial20240608.services;

import ni.factorizacion.parcial20240608.domain.dtos.SaveAppointmentDto;
import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.AppointmentState;
import ni.factorizacion.parcial20240608.domain.entities.User;

import java.util.List;

public interface AppointmentService {
    List<Appointment> findAll();
    List<Appointment> findByStatus(AppointmentState status);

    void save(SaveAppointmentDto dto, User patient);
}
