package com.chriscode.sprintbootnotion.Services;

import com.chriscode.sprintbootnotion.DTO.UserLoginDTO;
import com.chriscode.sprintbootnotion.Entity.User;

public interface UserAuthenticationService {

    User authenticate(UserLoginDTO user);

}
