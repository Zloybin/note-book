package dev.ezlobin.notebook.dto;

import dev.ezlobin.notebook.entity.Note;
import dev.ezlobin.notebook.entity.Notebook;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class NotebookDto {
    private String author;
    private String color;

    public NotebookDto() {
    }

    public NotebookDto(String author, String color) {
        this.author = author;
        this.color = color;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
