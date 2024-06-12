package ni.factorizacion.parcial20240608.domain.dtos;


import lombok.Data;
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