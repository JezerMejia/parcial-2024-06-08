package ni.factorizacion.parcial20240608.domain.dtos;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import ni.factorizacion.parcial20240608.domain.entities.User;

@Data
@SuperBuilder
public class SimpleUserDto {
    private String email;
    private String username;

    static public SimpleUserDto from(User user){
        return SimpleUserDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }
}
