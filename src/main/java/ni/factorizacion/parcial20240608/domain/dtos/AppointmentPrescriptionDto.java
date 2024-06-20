package ni.factorizacion.parcial20240608.domain.dtos;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class AppointmentPrescriptionDto {

    private String endDate;
    private String medicine;
    private String dose;

    static public AppointmentPrescriptionDto from(Prescription prescription){
        return AppointmentPrescriptionDto.builder()
                .endDate(prescription.getEndDate().toLocalDate().toString())
                .medicine(prescription.getMedicine())
                .dose(prescription.getDose())
                .build();
    }
}


