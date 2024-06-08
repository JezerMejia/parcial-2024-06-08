package ni.factorizacion.parcial20240608.services;

import ni.factorizacion.parcial20240608.domain.entities.Prescription;


public interface PrescriptionService {


    Prescription getById(String uuid);

}
