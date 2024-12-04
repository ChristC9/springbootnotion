package com.chriscode.sprintbootnotion.Services;

import com.chriscode.sprintbootnotion.Entity.Note;

import java.util.List;

public interface NoteService {

    Note save(Note note);
    Note update(Note note);
    String delete(String id);
    Note findbyId(String id);
    List<Note> findAll();
}
