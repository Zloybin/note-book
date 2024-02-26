package dev.ezlobin.notebook.controller;

import dev.ezlobin.notebook.dto.NoteDto;
import dev.ezlobin.notebook.entity.Note;
import dev.ezlobin.notebook.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @Autowired
    public NoteController() {
    }

    @GetMapping
    ResponseEntity<List<Note>> getAll(){
        List<Note> notes = noteService.findAll();
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    ResponseEntity<Note> findById(@PathVariable("id") String id){
        Note note = noteService.findById(id);
        return ResponseEntity.ok(note);
    }

    @PostMapping
    private ResponseEntity<Note> save(@RequestBody NoteDto noteDto){
        Note note = noteService.save(noteDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(note.getId())
                .toUri();
        return ResponseEntity.created(location).body(note);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Note> updateNote(@PathVariable("id")String id,
                                            @RequestBody NoteDto dto){
        Note note = noteService.update(id, dto);
        return ResponseEntity.ok(note);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable("id") String id){
        noteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    private ResponseEntity<Void> deleteAll(){
        noteService.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
