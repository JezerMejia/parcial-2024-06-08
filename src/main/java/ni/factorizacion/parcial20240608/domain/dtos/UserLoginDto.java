package ni.factorizacion.parcial20240608.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDto {
    @Email
    private String email;
    @NotEmpty
    private String password;
}
