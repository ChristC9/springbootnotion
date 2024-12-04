package com.chriscode.sprintbootnotion.Services;

import com.chriscode.sprintbootnotion.DAO.UserRepository;
import com.chriscode.sprintbootnotion.DTO.UserLoginDTO;
import com.chriscode.sprintbootnotion.Entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
public class UserAuthenticateServiceImp implements UserAuthenticationService{

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;


    public UserAuthenticateServiceImp(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public User authenticate(UserLoginDTO user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        return userRepository.findByEmail(user.getEmail()).orElseThrow();
    }
}
