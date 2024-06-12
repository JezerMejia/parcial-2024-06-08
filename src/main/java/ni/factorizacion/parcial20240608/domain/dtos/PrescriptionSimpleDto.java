package ni.factorizacion.parcial20240608.domain.dtos;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class PrescriptionSimpleDto {


    private LocalDateTime dateTime;
    private String medicine;
    private  String dose;


    static public PrescriptionSimpleDto from(Prescription prescription){
        return PrescriptionSimpleDto.builder()
                .dateTime(prescription.getEndDate())
                .medicine(prescription.getMedicine())
                .dose(prescription.getDose())
                .build();
    }
}


