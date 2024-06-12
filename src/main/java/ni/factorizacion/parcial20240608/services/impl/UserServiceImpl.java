package ni.factorizacion.parcial20240608.services.impl;

import jakarta.transaction.Transactional;
import ni.factorizacion.parcial20240608.domain.dtos.EditUserDto;
import ni.factorizacion.parcial20240608.domain.dtos.SaveUserDto;
import ni.factorizacion.parcial20240608.domain.dtos.ToggleRolDto;
import ni.factorizacion.parcial20240608.domain.entities.Role;
import ni.factorizacion.parcial20240608.domain.entities.Token;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.repositories.TokenRepository;
import ni.factorizacion.parcial20240608.repositories.UserRepository;
import ni.factorizacion.parcial20240608.services.RoleService;
import ni.factorizacion.parcial20240608.services.UserService;
import ni.factorizacion.parcial20240608.utils.Encrypt;
import ni.factorizacion.parcial20240608.utils.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public long getCount() {
        return userRepository.count();
    }

    @Override
    public void saveUser(SaveUserDto userDto) {
        var user = new User();

        user.setPassword(Encrypt.encryptPassword(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());

        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser (User user) {
        userRepository.delete(user);
        userRepository.flush();
    }

    @Override
    public void editUser (User user, EditUserDto userDto) {
        if(user.getPassword() !=null){
            user.setPassword(Encrypt.encryptPassword(userDto.getPassword()));
        }

        if (user.getUsername()!= null){
            user.setUsername(userDto.getUsername());
        }

        userRepository.save(user);
    }
    @Override
    public void toggleRole (User user, ToggleRolDto toggleRolDto) {
       Optional<Role> role = roleService.findById(toggleRolDto.getRole());

       if(role.isPresent()){
           if(user.getRoles().contains(role.get())){
               user.getRoles().remove(role.get());
           }else{
               user.getRoles().add(role.get());
           }
       }
        userRepository.save(user);
    }
    
    @Override
    public Boolean validAuthentication(User user, String password) {
        String encodedPassword = Encrypt.encryptPassword(password);
        return encodedPassword.equals(user.getPassword());
    }

    @Override
    public User findUserAuthenticated() {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return userRepository.findByEmail(user.getEmail());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Token registerToken(User user) throws Exception {
        cleanTokens(user);

        String tokenString = jwtTools.generateToken(user.getEmail());
        Token token = new Token(tokenString, user);

        tokenRepository.save(token);

        return token;
    }

    @Override
    public Boolean isTokenValid(User user, String token) {
        try {
            cleanTokens(user);
            List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

            tokens.stream()
                    .filter(tk -> tk.getContent().equals(token))
                    .findAny()
                    .orElseThrow(Exception::new);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void cleanTokens(User user) throws Exception {
        List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

        tokens.forEach(token -> {
            if (!jwtTools.verifyToken(token.getContent())) {
                token.setActive(false);
                tokenRepository.save(token);
            }
        });
    }
}
