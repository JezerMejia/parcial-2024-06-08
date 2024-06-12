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

    @Autowired
    private PrescriptionService servicePre;

    @GetMapping(path = "/schedule")
    public ResponseEntity<GeneralResponse<List<ScheduleSimpleDto>>> getAllMedicsAppointments() {
        var medicsAppointments = service.getAllMedicsAppointments();
        if (medicsAppointments.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "No Medics with Appointments found", medicsAppointments);
        }
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Found Medics with Appointments", medicsAppointments);
    }

   /*@GetMapping(path = "/prescription/{uuid}")
    public ResponseEntity<GeneralResponse<PrescriptionSimpleDto>> getPrescriptionById(@PathVariable UUID uuid) {
        try {
            var prescription = servicePre.getById(uuid);
            if (prescription == null) {
                return GeneralResponse.error404("Prescription not found");
            }
            return GeneralResponse.ok("Found Prescription", PrescriptionSimpleDto.from(prescription));
        } catch (IllegalArgumentException e) {
            return GeneralResponse.error400("Invalid UUID format: " + e.getMessage());
        } catch (Exception e) {
            return GeneralResponse.error500("Internal Server Error: " + e.getMessage());
        }
    }*/

}