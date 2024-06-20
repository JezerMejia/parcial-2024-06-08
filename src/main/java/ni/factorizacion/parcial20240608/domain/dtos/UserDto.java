package ni.factorizacion.parcial20240608.domain.dtos;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.parcial20240608.domain.entities.Role;
import ni.factorizacion.parcial20240608.domain.entities.User;

import java.util.List;

@Data
@SuperBuilder
public class UserDto {
    private String email;
    private String username;
    private List<String> roles;

    static public UserDto from(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .roles(user.getRoles().stream().map(Role::getName).toList())
                .build();
    }
}
