package ni.factorizacion.parcial20240608.services.impl;

import ni.factorizacion.parcial20240608.domain.dtos.SaveAppointmentDto;
import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.AppointmentState;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.repositories.AppointmentRepository;
import ni.factorizacion.parcial20240608.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> findByStatus(AppointmentState status) {
        return appointmentRepository.findByStatus(status);
    }

    @Override
    public void save(SaveAppointmentDto dto, User patient) {
        Appointment appointment = new Appointment();
        appointment.setReason(dto.getReason());
        appointment.setPatient(patient);
        appointment.setStartDate(dto.getDate());
        appointment.setStatus(AppointmentState.APPROVAL_PENDING);
        appointment.setRequestDate(LocalDateTime.now());

        appointmentRepository.save(appointment);
    }
}
