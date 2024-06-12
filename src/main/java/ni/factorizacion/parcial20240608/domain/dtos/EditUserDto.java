package ni.factorizacion.parcial20240608.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EditUserDto {
    private String username;

    @NotEmpty
    @Email
    private String email;

    @Pattern(regexp = "^\\d*\\w$")
    private String password;
}
