package dev.ezlobin.notebook.service;

import dev.ezlobin.notebook.dto.NoteDto;
import dev.ezlobin.notebook.dto.NotebookDto;
import dev.ezlobin.notebook.entity.Note;
import dev.ezlobin.notebook.entity.Notebook;
import dev.ezlobin.notebook.exception.WrongRequestDataException;
import dev.ezlobin.notebook.mapper.NoteMapper;
import dev.ezlobin.notebook.mapper.NotebookMapper;
import dev.ezlobin.notebook.repository.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NotebookService {

    private final NotebookRepository notebookRepository;
    private final NotebookMapper mapper;
    private final NoteMapper noteMapper;

    @Autowired
    public NotebookService(NotebookRepository notebookRepository, NotebookMapper mapper, NoteMapper noteMapper) {
        this.notebookRepository = notebookRepository;
        this.mapper = mapper;
        this.noteMapper = noteMapper;
    }


    public Notebook findById(String id) {

        if (id == null) {
            throw new WrongRequestDataException("An exception was thrown while getting entity! Entity ID cannot be null");
        }

        Optional<Notebook> notebooksOptional = notebookRepository.findById(id);

        if (notebooksOptional.isEmpty()) {
            String message = String.format("An exception was thrown while getting entity! An entity with the specified id was not found. Id: %s", id);
            throw new WrongRequestDataException(message);
        }

        return notebooksOptional.get();
    }

    public Notebook save(NotebookDto notebookDto) {
        if (notebookDto == null) {
            throw new WrongRequestDataException("An exception was thrown while saving notebook! The entity being stored can't be null");
        }

        Notebook notebook = mapper.toEntity(notebookDto);
        return notebookRepository.insert(notebook);
    }

    public Notebook update(NotebookDto notebookDto, String id) {

        if (notebookDto == null) {
            throw new WrongRequestDataException("An exception was thrown during entity update! Entity can't be null");
        }

        Notebook existingNotebook = findById(id);

        Notebook notebook = mapper.toEntity(notebookDto);
        notebook.setId(id);
        notebook.setCreationDate(existingNotebook.getCreationDate());

        return notebookRepository.save(notebook);
    }

    public Notebook update(Notebook notebook) {
        return notebookRepository.save(notebook);
    }

    public void deleteById(String id) {
        if (id == null) {
            throw new WrongRequestDataException("An exception was thrown during entity deletion! Id can't be null");
        }

        notebookRepository.deleteById(id);
    }

    public Notebook addNewNote(NoteDto noteDto, String id){
        Note note = noteMapper.toEntity(noteDto);
        Notebook notebook = findById(id);
        notebook.getNotes().add(note);
        return notebookRepository.save(notebook);
    }

    public List<Notebook> findAll() {
        return notebookRepository.findAll();
    }

    public void deleteAll() {
        notebookRepository.deleteAll();
    }
}
