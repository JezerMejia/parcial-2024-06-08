package ni.factorizacion.parcial20240608.services;

import ni.factorizacion.parcial20240608.domain.dtos.ApproveAppointmentDto;
import ni.factorizacion.parcial20240608.domain.dtos.SaveAppointmentDto;
import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.AppointmentMedicSpecialty;
import ni.factorizacion.parcial20240608.domain.entities.AppointmentState;
import ni.factorizacion.parcial20240608.domain.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentService {
    Optional<Appointment> findById(UUID uuid);

    List<Appointment> findAll();

    List<Appointment> findByStatus(AppointmentState status);

    void save(SaveAppointmentDto dto, User patient);

    void approve(ApproveAppointmentDto dto, Appointment appointment, List<AppointmentMedicSpecialty> amsList);

    void reject(Appointment appointment);

    void cancel(Appointment appointment);
}
