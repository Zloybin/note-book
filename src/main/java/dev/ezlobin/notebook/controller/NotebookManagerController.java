package dev.ezlobin.notebook.controller;

import dev.ezlobin.notebook.dto.NoteDto;
import dev.ezlobin.notebook.entity.Notebook;
import dev.ezlobin.notebook.service.NotebookManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/edit/{notebookId}")
public class NotebookManagerController {
    private NotebookManagerService service;
    @Autowired
    public NotebookManagerController(NotebookManagerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Notebook> addNewNote(@PathVariable("notebookId") String notebookId,
                                               @RequestBody NoteDto noteDto) {
        Notebook updatedNotebook = service.addNewNote(notebookId, noteDto);
        return ResponseEntity.ok(updatedNotebook);
    }

    @PutMapping
    public ResponseEntity<Notebook> addExistingNote(@PathVariable("notebookId") String notebookId,
                                                    @RequestParam String noteId){
        Notebook updatedNotebook = service.addExistedNote(notebookId, noteId);
        return ResponseEntity.ok(updatedNotebook);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteNote(@PathVariable("notebookId") String notebookId,
                                           @RequestParam String noteId){
        service.deleteNote(notebookId, noteId);
        return ResponseEntity.noContent().build();
    }
}
