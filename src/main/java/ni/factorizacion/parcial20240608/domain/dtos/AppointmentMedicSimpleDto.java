package ni.factorizacion.parcial20240608.domain.dtos;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.parcial20240608.domain.entities.*;

@Data
@SuperBuilder
public class AppointmentMedicSimpleDto {
    private AppointmentSimpleDto appointment;
    private SimpleUserDto user;
    private SpecialtySimpleDto specialty;

    static public AppointmentMedicSimpleDto from(AppointmentMedicSpecialty appointmentMedicSpecialty){
        return AppointmentMedicSimpleDto.builder()
                .appointment(AppointmentSimpleDto.from(appointmentMedicSpecialty.getAppointment()))
                .user(SimpleUserDto.from(appointmentMedicSpecialty.getMedic()))
                .specialty(SpecialtySimpleDto.fromDTO(appointmentMedicSpecialty.getSpecialty()))
                .build();
    }


}
