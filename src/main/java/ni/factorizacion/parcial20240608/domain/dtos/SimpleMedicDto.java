package ni.factorizacion.parcial20240608.domain.dtos;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.parcial20240608.domain.entities.AppointmentMedicSpecialty;

@Data
@SuperBuilder
public class SimpleMedicDto {
    private String email;
    private String username;
    private String specialty;

    public static SimpleMedicDto from(AppointmentMedicSpecialty ams) {
        return SimpleMedicDto.builder()
                .email(ams.getMedic().getEmail())
                .username(ams.getMedic().getUsername())
                .specialty(ams.getSpecialty().getName())
                .build();
    }
}
