package ni.factorizacion.parcial20240608.controllers;

import jakarta.validation.Valid;
import ni.factorizacion.parcial20240608.domain.dtos.*;
import ni.factorizacion.parcial20240608.domain.entities.*;
import ni.factorizacion.parcial20240608.services.AppointmentService;
import ni.factorizacion.parcial20240608.services.SpecialtyService;
import ni.factorizacion.parcial20240608.services.UserService;
import ni.factorizacion.parcial20240608.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/appointment/")
public class AppointmentRestController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private SpecialtyService specialtyService;

    @PostMapping(value = "/request", consumes = "application/json")
    public ResponseEntity<GeneralResponse<String>> requestAppointment(@RequestBody @Valid SaveAppointmentDto dto) {
        User user = userService.findUserAuthenticated();

        appointmentService.save(dto, user);

        return GeneralResponse.ok("Appointment created", "");
    }

    @PostMapping(value = "/approve", consumes = "application/json")
    @PreAuthorize("hasAuthority('RECP')")
    public ResponseEntity<GeneralResponse<String>> approveAppointment(@RequestBody @Valid ApproveAppointmentDto dto) {
        Optional<Appointment> appointment = appointmentService.findById(dto.getAppointmentId());
        if (appointment.isEmpty()) {
            return GeneralResponse.error404("The appointment does not exist");
        }

        List<AppointmentMedicSpecialty> amsList = new ArrayList<>();
        for (MedicAppointmentDto medicDto : dto.getMedics()) {
            AppointmentMedicSpecialty ams = new AppointmentMedicSpecialty();
            ams.setAppointment(appointment.get());

            User medic = userService.findByEmail(medicDto.getEmail());
            ams.setMedic(medic);

            Optional<Specialty> specialty = specialtyService.findById(medicDto.getSpecialtyCode());
            if (specialty.isEmpty()) {
                return GeneralResponse.error404("No specialty found for: " + medicDto.getSpecialtyCode());
            }
            ams.setSpecialty(specialty.get());
            amsList.add(ams);
        }

        appointmentService.approve(dto, appointment.get(), amsList);
        return GeneralResponse.ok("Appointment approved", "");
    }

    @PostMapping(value = "/reject")
    @PreAuthorize("hasAuthority('RECP')")
    public ResponseEntity<GeneralResponse<String>> rejectAppointment(@RequestBody UUID appointmentId) {
        Optional<Appointment> appointment = appointmentService.findById(appointmentId);
        if (appointment.isEmpty()) {
            return GeneralResponse.error404("The appointment does not exist");
        }
        appointmentService.reject(appointment.get());
        return GeneralResponse.ok("Appointment rejected", "");
    }

    @PostMapping(value = "/cancel")
    @PreAuthorize("hasAuthority('PTNT')")
    public ResponseEntity<GeneralResponse<String>> cancelAppointment(@RequestBody String appointmentId) {
        Optional<UUID> uuid = UUIDUtils.fromString(appointmentId);
        if (uuid.isEmpty()) {
            return GeneralResponse.error400("Incorrect UUID");
        }
        Optional<Appointment> appointment = appointmentService.findById(uuid.get());
        if (appointment.isEmpty()) {
            return GeneralResponse.error404("The appointment does not exist");
        }
        appointmentService.cancel(appointment.get());
        return GeneralResponse.ok("Appointment canceled", "");
    }

    @PostMapping("/finish")
    @PreAuthorize("hasAuthority('DOCT')")
    public ResponseEntity<GeneralResponse<String>> finishAppointment(@RequestBody String appointmentId) {
        Optional<UUID> uuid = UUIDUtils.fromString(appointmentId);
        if (uuid.isEmpty()) {
            return GeneralResponse.error400("Incorrect UUID");
        }
        Optional<Appointment> appointment = appointmentService.findById(uuid.get());
        if (appointment.isEmpty()) {
            return GeneralResponse.error404("The appointment does not exist");
        }
        User userDoctor = userService.findUserAuthenticated();
        boolean isMedicAssigned = appointment.get().getAppointmentMedicSpecialty().stream().anyMatch(ams -> ams.getMedic().getUuid().equals(userDoctor.getUuid()));

        if (appointment.get().getStatus() == AppointmentState.RUNNING && isMedicAssigned) {
            appointment.get().setStatus(AppointmentState.ENDED);
            appointmentService.finish(appointment.get());
            return GeneralResponse.ok("Appointment finished", "");
        } else {
            return GeneralResponse.error409("The appointment is not running");
        }
    }

    @GetMapping(value = "/own")
    @PreAuthorize("hasAuthority('PTNT')")
    public ResponseEntity<GeneralResponse<List<AppointmentDto>>> ownAppointment(@RequestParam(value = "status", required = false) AppointmentState status) {
        List<Appointment> appointments;
        if (status == null) {
            appointments = appointmentService.findAll();
        } else {
            appointments = appointmentService.findByStatus(status);
        }
        if (appointments.isEmpty()) {
            return GeneralResponse.error404("No appointments found");
        }

        List<AppointmentDto> appointmentDtos = appointments.stream().map(AppointmentDto::from).toList();

        return GeneralResponse.ok("Found appointments", appointmentDtos);
    }
}
