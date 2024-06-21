package ni.factorizacion.parcial20240608.controllers;

import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.dtos.output.SimpleUserDto;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/medics")
public class MedicRestController {
    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('RECP')")
    public ResponseEntity<GeneralResponse<List<SimpleUserDto>>> approveAppointment() {
        List<User> medics = userService.findMedics();
        if (medics.isEmpty()) {
            return GeneralResponse.ok("No medics found", List.of());
        }
        List<SimpleUserDto> simpleUserDtoList = medics.stream().map(SimpleUserDto::from).toList();

        return GeneralResponse.ok("Medics found", simpleUserDtoList);
    }
}
