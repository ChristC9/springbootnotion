package com.chriscode.sprintbootnotion.Controller;

import com.chriscode.sprintbootnotion.DTO.LoginResponseDTO;
import com.chriscode.sprintbootnotion.DTO.UserLoginDTO;
import com.chriscode.sprintbootnotion.Entity.User;
import com.chriscode.sprintbootnotion.Services.JwtService;
import com.chriscode.sprintbootnotion.Services.UserAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/")
public class UserAuthController {

    private final JwtService jwtService;

    private final UserAuthenticationService userAuthenticationService;

    public UserAuthController(JwtService jwtService, UserAuthenticationService userAuthenticationService) {
        this.jwtService = jwtService;
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("login/")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody UserLoginDTO userLoginDTO) {

        User authenticatedUser = userAuthenticationService.authenticate(userLoginDTO);

        String token = jwtService.generateToken(authenticatedUser);

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO().setToken(token).setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponseDTO);
    };
}
