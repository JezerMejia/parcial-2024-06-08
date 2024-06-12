package ni.factorizacion.parcial20240608.services;

import ni.factorizacion.parcial20240608.domain.dtos.SaveUserDto;
import ni.factorizacion.parcial20240608.domain.entities.Token;
import ni.factorizacion.parcial20240608.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> findAll();

    long getCount();

    User findByEmail(String username);

    void saveUser(SaveUserDto userDto);

    void toggleActive(User user);

    Boolean validAuthentication(User user, String password);

    User findUserAuthenticated();

    Token registerToken(User user) throws Exception;

    Boolean isTokenValid(User user, String token);

    void cleanTokens(User user) throws Exception;

    User findById(UUID uuid);
}
