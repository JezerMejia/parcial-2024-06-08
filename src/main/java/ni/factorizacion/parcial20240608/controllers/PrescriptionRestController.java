package ni.factorizacion.parcial20240608.controllers;


import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.dtos.SavePrescriptionDto;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import ni.factorizacion.parcial20240608.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clinic/prescriptions/")
public class PrescriptionRestController {

    @Autowired
    private PrescriptionService service;

    @GetMapping(path = "/{user_id}")
    //@PreAuthorize("hasAuthority('DOCT')")
    public ResponseEntity<GeneralResponse<Optional<Prescription>>> getByid(@PathVariable String user_id) {
        var  prescription = service.getById(user_id);
        if(prescription.isEmpty()){
            return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "No prescription found", prescription);

        }
        return GeneralResponse.ok("Found prescription", prescription);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<GeneralResponse<Prescription>> createPrescription(@RequestBody SavePrescriptionDto prescriptionDto) throws Exception{
        service.SavePrescription(prescriptionDto);
        return GeneralResponse.ok("Prescription saved", null);
    }

    @PutMapping(path = "/{id}")
    public void updatePrescription(@PathVariable String id, @RequestBody SavePrescriptionDto prescriptionDto) throws Exception{
        service.UpdatePrescription(id, prescriptionDto);
        GeneralResponse.ok("Prescription updated", null);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePrescription(@PathVariable String id) throws Exception{
        service.DeletePrescription(id);
        GeneralResponse.ok("Prescription deleted", null);
    }

}

