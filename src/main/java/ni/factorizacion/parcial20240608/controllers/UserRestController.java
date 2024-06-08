package ni.factorizacion.parcial20240608.controllers;

import jakarta.validation.Valid;
import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.dtos.SaveUserDto;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMN') and hasAuthority('RECP')")
    public ResponseEntity<GeneralResponse<List<User>>> findAllUsers() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return GeneralResponse.error404("No users found");
        }
        return GeneralResponse.ok("Found users", users);
    }

    @PostMapping(value = "/emailOrUsername")
    @PreAuthorize("hasAuthority('ADMN') or hasAuthority('DOCT')")
    public ResponseEntity<GeneralResponse<User>> findByEmailOrUsername(@RequestBody String emailOrUsername) {
        User user = userService.findByEmail(emailOrUsername);
        if (user == null) {
            user = userService.findByUsername(emailOrUsername);
        }
        if (user == null) {
            return GeneralResponse.error404("User not found");
        }
        return GeneralResponse.ok("User found", user);
    }
}
