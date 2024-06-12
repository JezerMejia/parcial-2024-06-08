package ni.factorizacion.parcial20240608.services.impl;

import ni.factorizacion.parcial20240608.domain.dtos.ApproveAppointmentDto;
import ni.factorizacion.parcial20240608.domain.dtos.SaveAppointmentDto;
import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.AppointmentMedicSpecialty;
import ni.factorizacion.parcial20240608.domain.entities.AppointmentState;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.repositories.AppointmentRepository;
import ni.factorizacion.parcial20240608.repositories.RoleRepository;
import ni.factorizacion.parcial20240608.repositories.SpecialtyRepository;
import ni.factorizacion.parcial20240608.repositories.UserRepository;
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
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public Optional<Appointment> findById(UUID uuid) {
        return appointmentRepository.findById(uuid);
    }

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
        appointment.setStartDate(dto.getDate().atStartOfDay());
        appointment.setStatus(AppointmentState.APPROVAL_PENDING);
        appointment.setRequestDate(LocalDateTime.now());

        appointmentRepository.save(appointment);
    }

    @Override
    public void approve(ApproveAppointmentDto dto, Appointment appointment, List<AppointmentMedicSpecialty> amsList) {
        appointment.setStatus(AppointmentState.RUNNING_PENDING);
        LocalDateTime startDate = appointment.getStartDate().toLocalDate().atTime(dto.getTime());
        appointment.setStartDate(startDate);
        LocalDateTime approx = startDate.plusMinutes(dto.getEstimateDuration());
        appointment.setApproxEndDate(approx);

        appointment.setAppointmentMedicSpecialty(amsList);
        appointmentRepository.save(appointment);

        // Add Patient role for the user
        User patient = appointment.getPatient();
        patient.addRole(roleRepository.findById("PTNT").get());
        userRepository.save(patient);
    }

    @Override
    public void reject(Appointment appointment) {
        appointment.setStatus(AppointmentState.REJECTED);
        appointmentRepository.save(appointment);
    }

    @Override
    public void cancel(Appointment appointment) {
        appointment.setStatus(AppointmentState.CANCELED);
        appointmentRepository.save(appointment);
    }
    @Override
    public void finish(Appointment appointment) {
        appointmentRepository.save(appointment);
    }
}
