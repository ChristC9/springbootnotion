package com.chriscode.sprintbootnotion.DAO;

import com.chriscode.sprintbootnotion.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    User createUser(User user);
    User updateUser(User user);
    List<User> getAllUsers();
    Optional <User> getUserById(String id);
    String deleteUserById(String id);
}
