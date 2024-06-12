package ni.factorizacion.parcial20240608.services.impl;

import ni.factorizacion.parcial20240608.domain.dtos.PrescriptionSimpleDto;
import ni.factorizacion.parcial20240608.domain.dtos.SavePrescriptionDto;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import ni.factorizacion.parcial20240608.repositories.PrescriptionRepository;
import ni.factorizacion.parcial20240608.repositories.UserRepository;
import ni.factorizacion.parcial20240608.services.PrescriptionService;
import ni.factorizacion.parcial20240608.types.ControlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Prescription> getById(String id) { return prescriptionRepository.findById(id);}

    @Override
    public Optional<List<PrescriptionSimpleDto>> getAllByUserId(UUID userId) {
        var user = userRepository.findById(userId);
        var allPrescriptions = prescriptionRepository.findAllByUser(user);

        return Optional.of(allPrescriptions.stream()
                .map(PrescriptionSimpleDto::from)
                .collect(Collectors.toList()));
    }

    @Override
    public void SavePrescription(SavePrescriptionDto prescriptionDto){
       Prescription prescription = new Prescription();
       prescription.setMedicine(prescriptionDto.getMedicine());
       prescription.setDose(prescriptionDto.getDose());
       prescription.setEndDate(prescriptionDto.getEndDate());

       prescriptionRepository.save(prescription);
    }

    @Override
    public void UpdatePrescription(String id, SavePrescriptionDto prescriptionDto) throws ControlException {
        Optional<Prescription> found = prescriptionRepository.findById(id);
        if(found.isEmpty()){
            throw new ControlException(HttpStatus.CONFLICT, "Prescription does not exists");
        }

        Prescription prescription = found.get();
        prescription.setEndDate(prescriptionDto.getEndDate());
        prescription.setMedicine(prescriptionDto.getMedicine());
        prescription.setDose(prescriptionDto.getDose());
        prescriptionRepository.save(prescription);

    }

    @Override
    public void DeletePrescription(String id) {
        boolean exists = prescriptionRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException(
                    "Prescription with the id +" + id +" does not exist");
        }
        prescriptionRepository.deleteById(id);
    }
}
