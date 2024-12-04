package com.chriscode.sprintbootnotion.Services;

import com.chriscode.sprintbootnotion.DAO.UserRepository;
import com.chriscode.sprintbootnotion.Entity.Note;
import com.chriscode.sprintbootnotion.Entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    private ObjectMapper mapper = new ObjectMapper();

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setNotes();
        return userRepo.createUser(user);
    }

    @Override
    public User update(User user) {
        Optional <User> oldUser = userRepo.getUserById(user.getId());
        if (oldUser.isPresent()) {
            User oldUserObj = oldUser.get();
            oldUserObj.setFirstName(user.getFirstName());
            oldUserObj.setLastName(user.getLastName());
            oldUserObj.setEmail(user.getEmail());
//            oldUserObj.setPassword(user.getPassword());
            oldUserObj.setRole(user.getRole());

            return userRepo.updateUser(oldUserObj);
        }else {
            throw  new RuntimeException("User not found with id " + user.getId());
        }

    }

    @Override
    public List<User> findAll() {
        return userRepo.getAllUsers();
    }

    @Override
    public User findbyId(String id) {

        Optional <User> result = userRepo.getUserById(id);
        User theUser = null;
        if (result.isPresent()){
            theUser = result.get();
        }else {
            throw new RuntimeException("User not found with id: " + id);
        }
        return theUser;
    }

    @Override
    public String delete(String id) {
        return userRepo.deleteUserById(id);
    }

    public List<Note> deserializeNotes(String notesJson) {
        try {
            return mapper.readValue(notesJson, new TypeReference<List<Note>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize notes", e);
        }
    }

}
