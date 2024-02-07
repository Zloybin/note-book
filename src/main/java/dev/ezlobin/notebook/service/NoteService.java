package dev.ezlobin.notebook.service;

import dev.ezlobin.notebook.dto.NoteDto;
import dev.ezlobin.notebook.entity.Note;
import dev.ezlobin.notebook.exception.WrongRequestDataException;
import dev.ezlobin.notebook.mapper.NoteMapper;
import dev.ezlobin.notebook.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class NoteService {
    private NoteRepository noteRepository;
    private NoteMapper noteMapper;

    @Autowired
    public NoteService(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    public List<Note> findAll(){
        return noteRepository.findAll();
    }

    public Note findById(String id) {
        if (id == null){
            throw new WrongRequestDataException("An exception was thrown while attempting to retrieve a note from the data store. The note ID cannot be null.");
        }

        Optional<Note> noteOptional = noteRepository.findById(id);

        if (noteOptional.isEmpty()){
            String message = String.format("An exception was thrown while attempting to retrieve a note from the data store. Note with id: %s does not exist.", id);
            throw new WrongRequestDataException(message);
        }

        return noteOptional.get();
    }

    public Note save(NoteDto noteDto) {
        if (noteDto == null) {
            throw new WrongRequestDataException("An exception was thrown while saving note! The entity being stored can't be null");
        }

        Note note = noteMapper.toEntity(noteDto);
        return noteRepository.insert(note);
    }

    public Note update(String id, NoteDto dto) {
        if (id == null){
            throw new WrongRequestDataException("An exception was thrown while updating note! The note ID can't be null");
        }

        if (dto == null){
            throw new WrongRequestDataException("An exception was thrown while updating note! The note dto can't be null");
        }

        if(!noteRepository.existsById(id)){
            String message = String.format("An exception was thrown while updating note! Note with id: %s was not exist.", id);
            throw new WrongRequestDataException(message);
        }
        Note existingNote = findById(id);
        Note note = noteMapper.toEntity(dto);
        note.setId(id);
        note.setCreationDate(existingNote.getCreationDate());
        return noteRepository.save(note);
    }

    public void deleteById(String id) {
        if (id == null){
            throw new WrongRequestDataException("An exception was thrown while deleting note! The note ID can't be null");
        }
        noteRepository.deleteById(id);
    }

    public void deleteAll() {
        noteRepository.deleteAll();
    }
}
