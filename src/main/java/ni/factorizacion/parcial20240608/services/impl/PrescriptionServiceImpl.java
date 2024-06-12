package ni.factorizacion.parcial20240608.services.impl;

import ni.factorizacion.parcial20240608.domain.dtos.HistorySimpleDto;
import ni.factorizacion.parcial20240608.domain.dtos.PrescriptionSimpleDto;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.repositories.PrecriptionRepository;
import ni.factorizacion.parcial20240608.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class PrescriptionServiceImpl implements PrescriptionService {

   @Autowired
    private PrecriptionRepository prescriptionRepository;
    @Override
    public List<PrescriptionSimpleDto> getAll() {
        return prescriptionRepository.findAll().stream().map(PrescriptionSimpleDto::from).toList();
    }

    @Override
    public Prescription getById(UUID uuid) {
        return prescriptionRepository.findById(uuid).orElse(null);
    }

}
