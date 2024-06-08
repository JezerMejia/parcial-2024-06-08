package ni.factorizacion.parcial20240608.controllers;

import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
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
@RequestMapping("/api/users/")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('PTNT')")
    public ResponseEntity<GeneralResponse<List<User>>> findAllUsers() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return GeneralResponse.error404("No users found");
        }
        return GeneralResponse.ok("Found users", users);
    }
}
