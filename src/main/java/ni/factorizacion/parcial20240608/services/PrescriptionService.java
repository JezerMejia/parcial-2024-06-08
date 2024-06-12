package ni.factorizacion.parcial20240608.services;

import ni.factorizacion.parcial20240608.domain.dtos.PrescriptionSimpleDto;
import ni.factorizacion.parcial20240608.domain.dtos.SavePrescriptionDto;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import ni.factorizacion.parcial20240608.types.ControlException;

import java.util.Optional;


public interface PrescriptionService {


    Optional<Prescription> getById(String uuid);


    void SavePrescription(SavePrescriptionDto prescriptionDto);
    void UpdatePrescription(String id, SavePrescriptionDto prescriptionDto) throws ControlException;
    void DeletePrescription(String id);

}
