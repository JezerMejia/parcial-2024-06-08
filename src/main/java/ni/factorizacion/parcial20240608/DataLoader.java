package ni.factorizacion.parcial20240608;

import ni.factorizacion.parcial20240608.domain.dtos.SaveUserDto;
import ni.factorizacion.parcial20240608.domain.entities.Role;
import ni.factorizacion.parcial20240608.services.RoleService;
import ni.factorizacion.parcial20240608.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public void populateUsers() {
        if (userService.getCount() > 0) {
            return;
        }
        SaveUserDto dto = new SaveUserDto();
        dto.setEmail("juan.perez@gmail.com");
        dto.setFirstName("Juan");
        dto.setLastName("Pérez");
        dto.setPassword("123456a");

        userService.saveUser(dto);
    }

    public void populateRoles() {
        if (roleService.getCount() > 0) {
            return;
        }
        Role[] roles = new Role[]{
                new Role("NONE", "No Auth"),
                new Role("PTNT", "Patient"),
                new Role("DOCT", "Doctor"),
                new Role("RECP", "Receptionist"),
                new Role("ADMN", "SysAdmin"),
        };
        Arrays.stream(roles).forEach(role -> roleService.saveRole(role));
    }

    public void run(ApplicationArguments args) {
        populateUsers();
        populateRoles();
    }
}
