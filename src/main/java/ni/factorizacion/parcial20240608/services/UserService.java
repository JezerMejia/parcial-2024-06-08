package ni.factorizacion.parcial20240608.services;

import ni.factorizacion.parcial20240608.domain.dtos.EditUserDto;
import ni.factorizacion.parcial20240608.domain.dtos.SaveUserDto;
import ni.factorizacion.parcial20240608.domain.entities.Token;
import ni.factorizacion.parcial20240608.domain.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    long getCount();

    User findByEmail(String email);
    User findByUsername(String username);

    void saveUser(SaveUserDto userDto);

    Boolean validAuthentication(User user, String password);

    User findUserAuthenticated();

    void deleteUser (User user);

    void editUser (User user, EditUserDto userDto);

    Token registerToken(User user) throws Exception;

    Boolean isTokenValid(User user, String token);

    void cleanTokens(User user) throws Exception;
}
