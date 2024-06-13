package ni.factorizacion.parcial20240608.controllers;

import ni.factorizacion.parcial20240608.domain.dtos.*;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.services.ClinicService;
import ni.factorizacion.parcial20240608.services.PrescriptionService;
import ni.factorizacion.parcial20240608.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clinic")
public class ClinicRestController {

    @Autowired
    private ClinicService service;

    @GetMapping(path = "/schedule")
    public ResponseEntity<GeneralResponse<List<AppointmentMedicSimpleDto>>> getAllMedicsAppointments() {
        List<AppointmentMedicSimpleDto> medicsAppointments = service.getAllAppointmentByMedic();
        if (medicsAppointments.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "No Medics with Appointments found", medicsAppointments);
        }
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Found Medics with Appointments", medicsAppointments);
    }

}