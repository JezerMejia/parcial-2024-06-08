package ni.factorizacion.parcial20240608.controllers;


import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import ni.factorizacion.parcial20240608.services.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/clinic/prescriptions/")

public class PrescriptionRestController {
    private PrescriptionService service;

    @GetMapping(path = "/{user_id}")
    @PreAuthorize("hasAuthority('DOCT')")
    public ResponseEntity<GeneralResponse<Optional<Prescription>>> getByid(@PathVariable String user_id) {
        var  prescription = service.getById(user_id);
        if(prescription.isEmpty()){
            return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "No prescription found", prescription);

        }
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Found prescription", prescription);
    }



}

