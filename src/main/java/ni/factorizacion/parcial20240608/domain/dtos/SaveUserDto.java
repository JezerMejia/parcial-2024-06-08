package ni.factorizacion.parcial20240608.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SaveUserDto {
    @NotEmpty
    private String username;
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp = "^\\d*\\w$", message = "{password.message}")
    private String password;
}
