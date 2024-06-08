package ni.factorizacion.parcial20240608.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SaveSpecialtyDto {

    @NotEmpty
    private String id;

    @NotEmpty
    private String name;

}
