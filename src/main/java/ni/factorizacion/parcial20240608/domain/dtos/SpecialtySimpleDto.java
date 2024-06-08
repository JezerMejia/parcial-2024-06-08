package ni.factorizacion.parcial20240608.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.parcial20240608.domain.entities.Specialty;

@Data
@SuperBuilder
public class SpecialtySimpleDto {
    private String name;

    static  public SpecialtySimpleDto fromDTO(Specialty specialty) {
        return SpecialtySimpleDto.builder()
                .name(specialty.getName())
                .build();
    }
}