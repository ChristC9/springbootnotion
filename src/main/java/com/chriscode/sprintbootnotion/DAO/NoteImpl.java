package com.chriscode.sprintbootnotion.DAO;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.chriscode.sprintbootnotion.Entity.Note;
import com.chriscode.sprintbootnotion.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteImpl implements NoteRepository {

    private DynamoDBMapper dynamoDBMapper;

    public NoteImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public Note createNote(Note note) {

//        User currentUser = dynamoDBMapper.load(User.class, note.getUser().getId());
//        note.setUser(currentUser);
        dynamoDBMapper.save(note);
        return note;
    }

    @Override
    public Note updateNote(Note note) {

        Note desiredNote = dynamoDBMapper.load(Note.class, note.getId());
        if (desiredNote != null) {
            desiredNote.setTitle(note.getTitle());
            desiredNote.setContent(note.getContent());
            dynamoDBMapper.save(desiredNote);
        }else {
            throw new RuntimeException("Note not found with id: " + note.getId());
        }
        return desiredNote;
    }

    @Override
    public String deleteNote(String id) {
        Note delNote = dynamoDBMapper.load(Note.class, id);
        dynamoDBMapper.delete(delNote);
        return "Note deleted successfully";
    }

    @Override
    public Note findNoteById(String id) {

        Note note = dynamoDBMapper.load(Note.class, id);
        return note;

    }

    @Override
    public List<Note> findAllNotes() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<Note> notes = dynamoDBMapper.scan(Note.class, scanExpression);
        return notes;
    }
}
