package ni.factorizacion.parcial20240608.controllers;

import jakarta.validation.Valid;
import ni.factorizacion.parcial20240608.domain.dtos.AppointmentDto;
import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.dtos.SaveAppointmentDto;
import ni.factorizacion.parcial20240608.domain.dtos.SimpleMedicDto;
import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.AppointmentMedicSpecialty;
import ni.factorizacion.parcial20240608.domain.entities.AppointmentState;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.services.AppointmentService;
import ni.factorizacion.parcial20240608.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appointment/")
public class AppointmentRestController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/request", consumes = "application/json")
    public ResponseEntity<GeneralResponse<String>> requestAppointment(@RequestBody @Valid SaveAppointmentDto dto) {
        User user = userService.findUserAuthenticated();

        appointmentService.save(dto, user);

        return GeneralResponse.ok("Appointment created", "");
    }

    @PostMapping(value = "/approve", consumes = "application/json")
    @PreAuthorize("hasAuthority('RECP')")
    public ResponseEntity<GeneralResponse<String>> approveAppointment() {
        return GeneralResponse.ok("Ola", "");
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

        List<AppointmentDto> dtos = new ArrayList<>();
        for (Appointment appointment : appointments) {
            AppointmentDto dto = new AppointmentDto();
            dto.setReason(appointment.getReason());
            dto.setStatus(appointment.getStatus().toString());
            dto.setRequestDate(appointment.getRequestDate().toString());
            dto.setStartDate(appointment.getStartDate().toString());
            if (appointment.getApproxEndDate() != null) {
                dto.setEndDate(appointment.getApproxEndDate().toString());
            }
            if (appointment.getEndDate() != null) {
                dto.setEndDate(appointment.getEndDate().toString());
            }
            dto.setPrescriptions(appointment.getPrescriptions());

            List<SimpleMedicDto> medics = new ArrayList<>();
            for (AppointmentMedicSpecialty ams : appointment.getAppointmentMedicSpecialty()) {
                SimpleMedicDto medicDto = new SimpleMedicDto();
                medicDto.setEmail(ams.getMedic().getEmail());
                medicDto.setUsername(ams.getMedic().getUsername());
                medicDto.setSpecialty(ams.getSpecialty().getName());
                medics.add(medicDto);
            }

            dto.setMedics(medics);

            dtos.add(dto);
        }

        return GeneralResponse.ok("Found appointments", dtos);
    }
}
