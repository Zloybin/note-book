package dev.ezlobin.notebook.controller;

import dev.ezlobin.notebook.dto.NotebookDto;
import dev.ezlobin.notebook.entity.Notebook;
import dev.ezlobin.notebook.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/notebooks")
public class NotebookController {
    private final NotebookService notebookService;

    @Autowired
    public NotebookController(NotebookService notebookService) {
        this.notebookService = notebookService;
    }

    @GetMapping
    public ResponseEntity<List<Notebook>> getAll(){
        List<Notebook> notebooks = notebookService.findAll();
        return ResponseEntity.ok(notebooks);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Notebook> getNotebook(@PathVariable("id") String id) {
        Notebook notebook = notebookService.findById(id);
        return ResponseEntity.ok(notebook);
    }

    @PostMapping
    public ResponseEntity<Notebook> createNotebook(@RequestBody NotebookDto notebookDto) {
        Notebook createdNotebook = notebookService.save(notebookDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdNotebook.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdNotebook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notebook> updateNotebook(@RequestBody NotebookDto notebookDto,
                                                   @PathVariable String id) {
        Notebook updatedNotebook = notebookService.update(notebookDto, id);
        return ResponseEntity.ok(updatedNotebook);
    }

    @DeleteMapping()
    private ResponseEntity<Void> deleteAll(){
        notebookService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable("id") String id){
        notebookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

