package com.chriscode.sprintbootnotion.Controller;


import com.chriscode.sprintbootnotion.DTO.NoteDTO;
import com.chriscode.sprintbootnotion.Entity.Note;
import com.chriscode.sprintbootnotion.Mappers.NoteDTOMapper;
import com.chriscode.sprintbootnotion.Services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/")
public class NoteController {

    NoteService noteService;
    private NoteDTOMapper noteDTOMapper;


    @Autowired
    public NoteController(NoteService noteService, NoteDTOMapper noteDTOMapper) {
        this.noteService = noteService;
        this.noteDTOMapper = noteDTOMapper;
    }

    @GetMapping("notes")
    public List<NoteDTO> getAllNotes() {
        List<Note> allNotes = noteService.findAll();
        return allNotes.stream()
                .map(noteDTOMapper)
                .collect(Collectors.toList());
    }

    @GetMapping("notes/{note_id}")
    public NoteDTO getNoteById(@PathVariable String note_id){

        Note noteDetails = noteService.findbyId(note_id);
        if (noteDetails == null){
            throw new RuntimeException("Note not found with id " + note_id);
        }
        NoteDTO note = new NoteDTO(noteDetails);
        return note;
    }

    @PostMapping("notes/create")
    public Note createNote(@RequestBody Note note){
        return noteService.save(note);
    }

    @PutMapping("notes")
    public Note updateNote(@RequestBody Note note){
        return noteService.update(note);
    }
}
