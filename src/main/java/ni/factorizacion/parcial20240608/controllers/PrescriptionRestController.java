package ni.factorizacion.parcial20240608.controllers;


import jakarta.validation.Valid;
import ni.factorizacion.parcial20240608.domain.dtos.AppointmentPrescriptionDto;
import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.dtos.PrescriptionSimpleDto;
import ni.factorizacion.parcial20240608.domain.dtos.SavePrescriptionDto;
import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.services.PrescriptionService;
import ni.factorizacion.parcial20240608.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clinic/prescriptions/")
public class PrescriptionRestController {

    @Autowired
    private PrescriptionService service;
    @Autowired
    private UserService userService;

    @GetMapping(path = "/{identifier}")
    //@PreAuthorize("hasAuthority('DOCT')")
    public ResponseEntity<GeneralResponse<List<AppointmentPrescriptionDto>>> getAllByUserId(@PathVariable String identifier) {
        User user = userService.findByEmail(identifier);
        if (user == null) {
            user = userService.findByUsername(identifier);
        }
        if (user == null) {
            return GeneralResponse.error404("User not found");
        }

        List<Prescription> prescriptions = service.getAllByUserId(user);
        List<AppointmentPrescriptionDto> appointmentDtos = prescriptions.stream().map(AppointmentPrescriptionDto::from).toList();

        return GeneralResponse.ok("Found prescription", appointmentDtos);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<GeneralResponse<Prescription>> createPrescription(@Valid @RequestBody SavePrescriptionDto prescriptionDto) {
        service.SavePrescription(prescriptionDto);
        return GeneralResponse.ok("Prescription saved", null);
    }

    @PutMapping(path = "/{id}")
    public void updatePrescription(@PathVariable String id, @RequestBody SavePrescriptionDto prescriptionDto) throws Exception {
        service.UpdatePrescription(id, prescriptionDto);
        GeneralResponse.ok("Prescription updated", null);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePrescription(@PathVariable String id) throws Exception {
        service.DeletePrescription(id);
        GeneralResponse.ok("Prescription deleted", null);
    }

}

