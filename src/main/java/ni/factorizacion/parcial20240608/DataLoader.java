package ni.factorizacion.parcial20240608;

import ni.factorizacion.parcial20240608.domain.dtos.SaveUserDto;
import ni.factorizacion.parcial20240608.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserService userService;

    public void run(ApplicationArguments args) {
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
}
