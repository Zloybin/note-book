package dev.ezlobin.notebook.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.ezlobin.notebook.enums.Color;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document
@Getter
@Setter
public class Notebook {
    @Id
    private String id;
    private String author;
    private Color color;
    @CreatedDate
    private LocalDateTime creationDate;
//    @DBRef
    private List<Note> notes;

    public Notebook(String author, Color color) {
        this.author = author;
        this.color = color;
        this.notes = new ArrayList<>();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
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
