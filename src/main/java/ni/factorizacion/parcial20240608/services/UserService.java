package ni.factorizacion.parcial20240608.services;

import ni.factorizacion.parcial20240608.domain.dtos.input.EditUserDto;
import ni.factorizacion.parcial20240608.domain.dtos.input.SaveUserDto;
import ni.factorizacion.parcial20240608.domain.entities.Role;
import ni.factorizacion.parcial20240608.domain.entities.Token;
import ni.factorizacion.parcial20240608.domain.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    long getCount();

    User findByEmail(String email);

    User findByUsername(String username);

    List<User> findMedics();

    List<User> findPatients();

    User saveUser(SaveUserDto userDto);

    Boolean validAuthentication(User user, String password);

    User findUserAuthenticated();

    void deleteUser(User user);

    void editUser(User user, EditUserDto userDto);

    void toggleRole(User user, Role role);

    Token registerToken(User user) throws Exception;

    Boolean isTokenValid(User user, String token);

    void cleanTokens(User user) throws Exception;
}
