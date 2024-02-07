package dev.ezlobin.notebook.repository;

import dev.ezlobin.notebook.entity.Notebook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotebookRepository extends MongoRepository<Notebook, String> {

}
