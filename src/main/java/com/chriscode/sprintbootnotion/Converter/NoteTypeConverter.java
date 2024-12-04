package com.chriscode.sprintbootnotion.Converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.chriscode.sprintbootnotion.Entity.Note;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class NoteTypeConverter implements DynamoDBTypeConverter<String, List<Note>> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convert(List<Note> notes) {
        try {
            return mapper.writeValueAsString(notes);
        } catch (IOException e) {
            throw new RuntimeException("Unable to serialize notes list", e);
        }
    }

    @Override
    public List<Note> unconvert(String jsonString) {
        try {
            return mapper.readValue(jsonString, new TypeReference<List<Note>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Could not convert string to notes", e);
        }
    }
}
