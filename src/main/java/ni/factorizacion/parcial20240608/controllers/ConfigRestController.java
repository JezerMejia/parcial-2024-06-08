package ni.factorizacion.parcial20240608.controllers;

import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.dtos.input.ToggleRolDto;
import ni.factorizacion.parcial20240608.domain.entities.Role;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.services.RoleService;
import ni.factorizacion.parcial20240608.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ConfigRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/config/user-role")
    @PreAuthorize("hasAuthority('ADMN')")
    public ResponseEntity<GeneralResponse<String>> toggleRole(@RequestBody ToggleRolDto userDto) {
        User user = userService.findByEmail(userDto.getIdentifier());
        if (user == null) {
            user = userService.findByUsername(userDto.getIdentifier());
        }
        if (user == null) {
            return GeneralResponse.error404("No user found");
        }
        switch (userDto.getRole()) {
            case "DOCT":
            case "RECP":
                break;
            default:
                return GeneralResponse.error400("Invalid role assignment");
        }
        Optional<Role> role = roleService.findById(userDto.getRole());
        if (role.isEmpty()) {
            return GeneralResponse.error404("Invalid role");
        }

        userService.toggleRole(user, role.get());
        return GeneralResponse.ok("Role toggled", null);
    }
}
