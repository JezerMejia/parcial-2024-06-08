package ni.factorizacion.parcial20240608.controllers;


import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import ni.factorizacion.parcial20240608.services.PrescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clinic/prescriptions/")

public class PrescriptionRestController {
    private PrescriptionService service;

    @GetMapping(path = "/{user_id}")
    public ResponseEntity<GeneralResponse<Prescription>> getByid(@PathVariable String user_id) {
        Prescription prescription = service.getById(user_id);
        if(prescription == null){
            return GeneralResponse.error404("Preescripcoin no encontrada");
        }
        return GeneralResponse.ok("Preescripcion encontrada", prescription);
    }

}
