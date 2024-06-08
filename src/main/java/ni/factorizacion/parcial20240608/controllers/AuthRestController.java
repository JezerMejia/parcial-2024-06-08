package ni.factorizacion.parcial20240608.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.dtos.TokenDto;
import ni.factorizacion.parcial20240608.domain.dtos.UserLoginDto;
import ni.factorizacion.parcial20240608.domain.entities.Token;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

        if (user == null || !user.getActive()) {
            return GeneralResponse.getResponse(HttpStatus.UNAUTHORIZED, "User not found", null);
        }

        if (!service.validAuthentication(user, info.getPassword())) {
            return GeneralResponse.getResponse(HttpStatus.UNAUTHORIZED, "Invalid authentication", null);
        }

        try {
            Token token = service.registerToken(user);
            return GeneralResponse.getResponse(HttpStatus.OK, "Valid authentication", new TokenDto(token));
        } catch (Exception e) {
            e.printStackTrace();
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null);
        }
    }
}
