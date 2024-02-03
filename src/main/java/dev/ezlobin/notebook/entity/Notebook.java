package dev.ezlobin.notebook.entity;

import dev.ezlobin.notebook.enums.Color;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;

@Document
@Getter
@Setter
public class Notebook {
    @Id
    private String id;
    private String author;
    private Color color;
    private  LocalDate creationDate;

    public Notebook(String author, Color color) {
        this.author = author;
        this.color = color;
        this.creationDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", color=" + color +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notebook notebook = (Notebook) o;
        return Objects.equals(id, notebook.id) && Objects.equals(author, notebook.author) && color == notebook.color && Objects.equals(creationDate, notebook.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, color, creationDate);
    }
}
