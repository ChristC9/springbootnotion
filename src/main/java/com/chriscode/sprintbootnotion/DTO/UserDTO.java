package com.chriscode.sprintbootnotion.DTO;

import com.chriscode.sprintbootnotion.Entity.Note;
import com.chriscode.sprintbootnotion.Entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;
    private List<NoteDTO> notes;


    public UserDTO(User user) {
        this.id = user.getId();
        this.firstname = user.getFirstName();
        this.lastname = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.notes = user.getNotes().stream().map(NoteDTO::new).collect(Collectors.toList());
    }

    public UserDTO(String id, String firstname, String lastname, String email, String password, String role, List<NoteDTO> notes) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.notes = notes;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<NoteDTO> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteDTO> notes) {
        this.notes = notes;
    }
}
