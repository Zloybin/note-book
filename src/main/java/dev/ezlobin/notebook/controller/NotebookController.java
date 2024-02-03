package dev.ezlobin.notebook.controller;

import dev.ezlobin.notebook.dto.NotebookDto;
import dev.ezlobin.notebook.entity.Notebook;
import dev.ezlobin.notebook.mapper.NotebookMapper;
import dev.ezlobin.notebook.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/notebooks")
public class NotebookController {
    private NotebookService notebookService;

    @Autowired
    public NotebookController(NotebookService notebookService) {
        this.notebookService = notebookService;
    }

    @GetMapping("/{id}")
    public Notebook getNotebook(@PathVariable("id") String id) {
        return notebookService.findById(id);
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
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedNotebook.getId())
                .toUri();
        return ResponseEntity.created(location).body(updatedNotebook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable("id") String id){
        notebookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

