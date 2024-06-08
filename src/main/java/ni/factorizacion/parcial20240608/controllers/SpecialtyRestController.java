package ni.factorizacion.parcial20240608.controllers;

import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.dtos.SaveSpecialtyDto;
import ni.factorizacion.parcial20240608.domain.entities.Specialty;
import ni.factorizacion.parcial20240608.services.SpecialtyService;
import ni.factorizacion.parcial20240608.types.ControlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/specialties")
public class SpecialtyRestController {
    @Autowired
    private SpecialtyService service;

    @GetMapping
    public ResponseEntity<GeneralResponse<List<Specialty>>> getAllSpecialties(){
        var specialties = service.findAll();

        if(specialties.isEmpty()){
            return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "No specialties found", specialties);
        }
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Found specialties", specialties);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<GeneralResponse<Specialty>> createSpecialty(@RequestBody SaveSpecialtyDto specialty) throws ControlException {
        service.SaveSpecialty(specialty);
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Specialty saved", null);
    }

    @DeleteMapping(path = "/{id}")
    public void removeSpecialty(@PathVariable("id") String id) throws ControlException {
        service.DeleteSpecialty(id);
        GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Specialty removed", null);
    }

    @PutMapping(path = "/{id}")
    public void updateSpecialty(@PathVariable("id") String id, @RequestBody SaveSpecialtyDto specialty) throws ControlException {
        service.UpdateSpecialty(id, specialty);
        GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Specialty updated", null);
    }
}