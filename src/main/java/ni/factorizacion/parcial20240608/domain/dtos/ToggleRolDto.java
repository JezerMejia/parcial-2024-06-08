package ni.factorizacion.parcial20240608.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ToggleRolDto {
    @NotEmpty
    private String identifier;
    @NotEmpty
    private String role;
}
