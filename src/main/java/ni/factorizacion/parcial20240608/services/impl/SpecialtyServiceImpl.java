package ni.factorizacion.parcial20240608.services.impl;

import ni.factorizacion.parcial20240608.domain.dtos.input.SaveSpecialtyDto;
import ni.factorizacion.parcial20240608.domain.entities.Specialty;
import ni.factorizacion.parcial20240608.repositories.SpecialtyRepository;
import ni.factorizacion.parcial20240608.services.SpecialtyService;
import ni.factorizacion.parcial20240608.types.ControlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public Optional<Specialty> findById(String id) {
        return specialtyRepository.findById(id);
    }

    @Override
    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }

    @Override
    public long getCount() {
        return specialtyRepository.count();
    }

    @Override
    public void SaveSpecialty(SaveSpecialtyDto specialtyDto) {
        Specialty specialty = new Specialty();
        specialty.setId(specialtyDto.getId());
        specialty.setName(specialtyDto.getName());

        specialtyRepository.save(specialty);
    }


    @Override
    public void DeleteSpecialty(String id) {
        boolean exists = specialtyRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException(
                    "Specialty with the id +" + id +" does not exist");
        }
        specialtyRepository.deleteById(id);
    }

    @Override
    public void UpdateSpecialty(String id, SaveSpecialtyDto saveSpecialtyDto) throws ControlException {
        Optional<Specialty> found = specialtyRepository.findById(id);
        if (found.isEmpty()) {
            throw new ControlException(HttpStatus.CONFLICT, "Specialty does not exists");
        }
        Specialty foundSpecialty = found.get();
        foundSpecialty.setName(saveSpecialtyDto.getName());
        specialtyRepository.save(foundSpecialty);
    }
}
