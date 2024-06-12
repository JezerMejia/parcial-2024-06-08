package ni.factorizacion.parcial20240608.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.dtos.SaveUserDto;
import ni.factorizacion.parcial20240608.domain.dtos.TokenDto;
import ni.factorizacion.parcial20240608.domain.dtos.UserLoginDto;
import ni.factorizacion.parcial20240608.domain.entities.Token;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth/", consumes = "application/json")
@RequiredArgsConstructor
public class AuthRestController {
    private final UserService service;
    @PostMapping("/login")
    public ResponseEntity<GeneralResponse<TokenDto>> login(@Valid @RequestBody UserLoginDto info) {
        User user = service.findByEmail(info.getEmail());

        if (user == null) {
            return GeneralResponse.error401("User not found");
        }

        if (!service.validAuthentication(user, info.getPassword())) {
            return GeneralResponse.error401("Invalid authentication");
        }

        try {
            Token token = service.registerToken(user);
            return GeneralResponse.ok("Valid authentication", new TokenDto(token));
        } catch (Exception e) {
            e.printStackTrace();
            return GeneralResponse.error500("Error");
        }
    }

    @PostMapping(value="/register", consumes = "application/json")
    public ResponseEntity<GeneralResponse<User>> register(@Valid @RequestBody SaveUserDto user) {
        service.saveUser(user);
        return GeneralResponse.ok("User registered", service.findByEmail(user.getEmail()));
    }
}
