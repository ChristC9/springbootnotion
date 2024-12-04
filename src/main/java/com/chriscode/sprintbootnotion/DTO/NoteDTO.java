package com.chriscode.sprintbootnotion.DTO;


import com.chriscode.sprintbootnotion.Entity.Note;

public class NoteDTO {

    private String id;
    private String title;
    private String content;


    public NoteDTO() {}

    public NoteDTO(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public NoteDTO(Note note) {
        this.id = note.getId();
        this.title = note.getTitle();
        this.content = note.getContent();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
