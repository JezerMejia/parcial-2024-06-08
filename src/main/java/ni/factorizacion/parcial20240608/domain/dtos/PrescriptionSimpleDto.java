package ni.factorizacion.parcial20240608.domain.dtos;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class PrescriptionSimpleDto {

    private LocalDateTime endDate;
    private String medicine;
    private String dose;
    private AppointmentSimpleDto appointment;

    static public PrescriptionSimpleDto from(Prescription prescription){
        return PrescriptionSimpleDto.builder()
                .endDate(prescription.getEndDate())
                .medicine(prescription.getMedicine())
                .dose(prescription.getDose())
                .appointment(AppointmentSimpleDto.from(prescription.getAppointment()))
                .build();
    }
}


