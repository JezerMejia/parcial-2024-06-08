package ni.factorizacion.parcial20240608.services;

import ni.factorizacion.parcial20240608.domain.dtos.HistorySimpleDto;
import ni.factorizacion.parcial20240608.domain.dtos.PrescriptionSimpleDto;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import ni.factorizacion.parcial20240608.domain.entities.User;

import java.util.List;
import java.util.UUID;


public interface PrescriptionService {
    List<PrescriptionSimpleDto> getAll();
    Prescription getById(UUID uuid);
}
