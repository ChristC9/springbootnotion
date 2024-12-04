package com.chriscode.sprintbootnotion.Services;

import com.chriscode.sprintbootnotion.DAO.NoteRepository;
import com.chriscode.sprintbootnotion.Entity.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    @Override
    public Note save(Note note) {
        return noteRepository.createNote(note);
    }

    @Override
    public Note update(Note note) {
        return noteRepository.updateNote(note);
    }

    @Override
    public String delete(String id) {
        return noteRepository.deleteNote(id);
    }

    @Override
    public Note findbyId(String id) {
        return noteRepository.findNoteById(id);
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAllNotes();
    }
}
