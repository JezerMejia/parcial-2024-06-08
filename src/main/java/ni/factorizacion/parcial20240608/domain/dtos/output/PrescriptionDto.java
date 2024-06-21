package ni.factorizacion.parcial20240608.domain.dtos.output;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;

@Data
@SuperBuilder
public class PrescriptionDto {

    private String endDate;
    private String medicine;
    private String dose;

    static public PrescriptionDto from(Prescription prescription){
        return PrescriptionDto.builder()
                .endDate(prescription.getEndDate().toLocalDate().toString())
                .medicine(prescription.getMedicine())
                .dose(prescription.getDose())
                .build();
    }
}


