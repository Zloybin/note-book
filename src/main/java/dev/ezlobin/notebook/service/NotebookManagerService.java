package dev.ezlobin.notebook.service;

import dev.ezlobin.notebook.dto.NoteDto;
import dev.ezlobin.notebook.entity.Note;
import dev.ezlobin.notebook.entity.Notebook;
import dev.ezlobin.notebook.exception.WrongRequestDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotebookManagerService {
    private final NotebookService notebookService;
    private final NoteService noteService;

    @Autowired
    public NotebookManagerService(NotebookService notebookService, NoteService noteService) {
        this.notebookService = notebookService;
        this.noteService = noteService;
    }

    public Notebook addNewNote(String id, NoteDto noteDto) {
        Notebook notebook = notebookService.findById(id);
        Note note = noteService.save(noteDto);
        notebook.getNotes().add(note);
        return notebookService.update(notebook);
    }

    public Notebook addExistedNote(String id, String noteId) {
        Notebook notebook = notebookService.findById(id);
        Note note = noteService.findById(noteId);
        notebook.getNotes().add(note);
        return notebookService.update(notebook);
    }

    public void deleteNote(String notebookId, String noteId) {
        Notebook notebook = notebookService.findById(notebookId);

        if (!isConsist(noteId, notebook)){
            String message = String.format("An exception was thrown while attempting to delete a note from a notebook. Notebook with id: %s does not contain a note with id: %s.", notebookId, noteId);
            throw new WrongRequestDataException(message);
        }

        Note note = noteService.findById(noteId);
        notebook.getNotes().remove(note);
        notebookService.update(notebook);
    }

    private boolean isConsist(String noteId, Notebook notebook) {
        return notebook.getNotes().stream()
                .map(note -> note.getId())
                .anyMatch(id -> id.equals(noteId));
    }
}
