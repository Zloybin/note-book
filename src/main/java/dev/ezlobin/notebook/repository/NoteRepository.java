package dev.ezlobin.notebook.repository;

import dev.ezlobin.notebook.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {
}
