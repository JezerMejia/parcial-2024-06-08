package ni.factorizacion.parcial20240608.controllers;

import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.dtos.ToggleRolDto;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigRestController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/config/user-role")
    @PreAuthorize("hasAuthority('ADMN')")
    public ResponseEntity<GeneralResponse<String>> toggleRole(@RequestBody ToggleRolDto userDto) {
        User user = userService.findByEmail(userDto.getEmail());
        userService.toggleRole(user, userDto);
        return GeneralResponse.ok("Role edited", null);
    }
}
