package com.chriscode.sprintbootnotion.DAO;

import com.chriscode.sprintbootnotion.Entity.Note;


import java.util.List;

public interface NoteRepository{

    Note createNote(Note note);
    List<Note> findAllNotes();
    Note findNoteById(String id);
    Note updateNote(Note note);
    String deleteNote(String id);

}
