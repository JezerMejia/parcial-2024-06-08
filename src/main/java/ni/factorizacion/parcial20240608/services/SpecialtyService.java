package ni.factorizacion.parcial20240608.services;

import ni.factorizacion.parcial20240608.domain.dtos.SaveSpecialtyDto;
import ni.factorizacion.parcial20240608.domain.entities.Specialty;
import ni.factorizacion.parcial20240608.types.ControlException;

import java.util.List;
import java.util.Optional;

public interface SpecialtyService {
    Optional<Specialty> findById(String id);

    List<Specialty> findAll();

    long getCount();

    void SaveSpecialty(SaveSpecialtyDto specialtyDto);

    void DeleteSpecialty(String id);

    void UpdateSpecialty(String id, SaveSpecialtyDto saveSpecialtyDto) throws ControlException;
}
