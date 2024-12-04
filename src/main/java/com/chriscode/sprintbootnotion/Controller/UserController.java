package com.chriscode.sprintbootnotion.Controller;

import com.chriscode.sprintbootnotion.DTO.UserDTO;
import com.chriscode.sprintbootnotion.Entity.Note;
import com.chriscode.sprintbootnotion.Entity.User;
import com.chriscode.sprintbootnotion.Mappers.NoteDTOMapper;
import com.chriscode.sprintbootnotion.Mappers.UserDTOMapper;
import com.chriscode.sprintbootnotion.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/")
public class UserController {

    UserService userService;
    private NoteDTOMapper noteDTOMapper;
    private UserDTOMapper userDTOMapper;

    @Autowired
    public UserController(UserService theUserService, NoteDTOMapper theNoteDTOMapper, UserDTOMapper theUserDTOMapper) {
        this.userService = theUserService;
        this.noteDTOMapper = theNoteDTOMapper;
        this.userDTOMapper = theUserDTOMapper;
    }

    @PostMapping("user/create")
    public User addUser(@RequestBody User user){

        User newUserData = userService.save(user);
        return newUserData;

    }

    @GetMapping("users")
    public List<UserDTO> getUsers(){
        List<User> allUsers = userService.findAll();
        return allUsers.stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    @GetMapping("users/{user_id}")
    public UserDTO getUserByid(@PathVariable String user_id){

        User specificUser = userService.findbyId(user_id);

        if (specificUser == null) {
            throw new RuntimeException("User with id " + user_id + " not found");
        }

        UserDTO userDTO = new UserDTO(specificUser);
        return userDTO;
    }

    @PutMapping("users")
    public User updateUser(@RequestBody User user){

        User updateUserData = userService.update(user);
        return updateUserData;

    }

//    @DeleteMapping("user/{user_Id}")
//    public String deleteUser(@PathVariable long user_id){
//
//        User deleteUserData = userService.getUserById(user_id);
//        if (deleteUserData == null){
//            throw new RuntimeException("User with id " + user_id + " not found");
//        }
//        return "User id "+ user_id + " deleted successfully";
//
//
//    }

    @DeleteMapping("users")
    public ResponseEntity<String> deleteUser(@RequestBody User user){

        try {
            userService.delete(user.getId());
            return new ResponseEntity<>(
                    "User with id " + user.getId() + " is deleted", HttpStatus.OK
            );
        } catch (Exception e) {

            return new ResponseEntity<String>(
                "User with id " + user.getId() + " not found", HttpStatus.NOT_FOUND
            );
        }

    }

}
