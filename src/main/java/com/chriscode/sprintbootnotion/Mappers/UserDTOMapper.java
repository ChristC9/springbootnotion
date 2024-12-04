package com.chriscode.sprintbootnotion.Mappers;

import com.chriscode.sprintbootnotion.DTO.UserDTO;
import com.chriscode.sprintbootnotion.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {

    @Autowired
    private NoteDTOMapper noteDTOMapper;


    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getNotes().stream()
                        .map(noteDTOMapper)
                        .collect(Collectors.toList())
        );
    }
}
