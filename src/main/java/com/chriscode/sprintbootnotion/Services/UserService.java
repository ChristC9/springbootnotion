package com.chriscode.sprintbootnotion.Services;

import com.chriscode.sprintbootnotion.Entity.Note;
import com.chriscode.sprintbootnotion.Entity.User;

import java.util.List;

public interface UserService {

    User save(User user);
    User update(User user);
    List<User> findAll();
    User findbyId(String id);
    String delete(String id);
    List<Note> deserializeNotes(String json);
}
