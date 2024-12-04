package com.chriscode.sprintbootnotion.Mappers;

import com.chriscode.sprintbootnotion.DTO.NoteDTO;
import com.chriscode.sprintbootnotion.Entity.Note;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class NoteDTOMapper implements Function<Note,NoteDTO> {


    @Override
    public NoteDTO apply(Note note) {
        return new NoteDTO(
                note.getId(),
                note.getTitle(),
                note.getContent()
        );
    }
}
