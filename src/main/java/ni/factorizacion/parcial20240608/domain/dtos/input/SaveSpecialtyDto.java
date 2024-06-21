package ni.factorizacion.parcial20240608.domain.dtos.input;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SaveSpecialtyDto {

    @NotEmpty
    private String id;

    @NotEmpty
    private String name;

}
