package ni.factorizacion.parcial20240608.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class PrescriptionSimpleDto {


    private LocalDateTime fecha;
    private String medicamento;
    private  String dosis;


    static public PrescriptionSimpleDto from(Prescription prescription){
        return PrescriptionSimpleDto.builder()
                .fecha(prescription.getEndDate())
                .medicamento(prescription.getMedicine())
                .dosis(prescription.getDose())
                .build();
    }
}
