package dev.ezlobin.notebook.mapper;

import dev.ezlobin.notebook.dto.NoteDto;
import dev.ezlobin.notebook.entity.Note;
import dev.ezlobin.notebook.exception.NoteMapperException;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper implements Mapper<Note, NoteDto> {

    @Override
    public Note toEntity(NoteDto noteDto) {
        if(noteDto.getContent() == null){
                final String message = "Content can't be null!";
                throw new NoteMapperException(message);
            }
        return new Note(noteDto.getContent());
    }
}
