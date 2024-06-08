package ni.factorizacion.parcial20240608.controllers;

import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.dtos.SaveSpecialtyDto;
import ni.factorizacion.parcial20240608.domain.entities.Specialty;
import ni.factorizacion.parcial20240608.services.SpecialtyService;
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
    public ResponseEntity<GeneralResponse<Specialty>> createSpecialty(@RequestBody SaveSpecialtyDto specialty) throws Exception {
        service.SaveSpecialty(specialty);
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Specialty saved", null);
    }

    @DeleteMapping(path = "/{uuid}")
    public void removeSpecialty(@PathVariable("uuid") String id) throws Exception {
        service.DeleteSpecialty(id);
    }

    @PutMapping(path = "/{uuid}")
    public void updateSpecialty(@PathVariable("uuid") String id, @RequestBody SaveSpecialtyDto specialty) throws Exception {
        service.UpdateSpecialty(id, specialty);
    }
}