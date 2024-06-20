package ni.factorizacion.parcial20240608.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.dtos.SaveUserDto;
import ni.factorizacion.parcial20240608.domain.dtos.UserDto;
import ni.factorizacion.parcial20240608.domain.dtos.UserLoginDto;
import ni.factorizacion.parcial20240608.domain.entities.Token;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthRestController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse<String>> login(@Valid @RequestBody UserLoginDto info) {
        User user = userService.findByEmail(info.getIdentifier());
        if (user == null) {
            user = userService.findByUsername(info.getIdentifier());
        }
        if (user == null) {
            return GeneralResponse.error404("No user found");
        }

        if (!userService.validAuthentication(user, info.getPassword())) {
            return GeneralResponse.error401("Invalid authentication");
        }

        try {
            Token token = userService.registerToken(user);
            return GeneralResponse.ok("Valid authentication", token.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            return GeneralResponse.error500("Error");
        }
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<GeneralResponse<User>> register(@Valid @RequestBody SaveUserDto user) {
        userService.saveUser(user);
        return GeneralResponse.ok("User registered", userService.findByEmail(user.getEmail()));
    }

    @RequestMapping("/self")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GeneralResponse<UserDto>> getUserInfo() {
        User user = userService.findUserAuthenticated();
        if (user == null) {
            return GeneralResponse.error401("No authenticated user");
        }
        UserDto userDto = UserDto.from(user);
        return GeneralResponse.ok("Found user", userDto);
    }

}
