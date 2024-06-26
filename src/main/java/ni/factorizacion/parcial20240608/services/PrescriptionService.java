package ni.factorizacion.parcial20240608.services;

import ni.factorizacion.parcial20240608.domain.dtos.input.SavePrescriptionDto;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.types.ControlException;

import java.util.List;
import java.util.Optional;


public interface PrescriptionService {
    Optional<Prescription> getById(String uuid);

    List<Prescription> getAllByUserId(User user);

    void SavePrescription(SavePrescriptionDto prescriptionDto);

    void UpdatePrescription(String id, SavePrescriptionDto prescriptionDto) throws ControlException;

    void DeletePrescription(String id);

}
