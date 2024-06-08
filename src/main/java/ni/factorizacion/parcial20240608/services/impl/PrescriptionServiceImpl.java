package ni.factorizacion.parcial20240608.services.impl;

import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import ni.factorizacion.parcial20240608.repositories.PrecriptionRepository;
import ni.factorizacion.parcial20240608.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrecriptionRepository prescriptionRepository;

    @Override
    public Prescription getById(String id) {
        return prescriptionRepository.findById(id).get();
    }




}
