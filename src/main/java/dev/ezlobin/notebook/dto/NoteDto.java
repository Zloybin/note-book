package dev.ezlobin.notebook.dto;

import lombok.Data;

@Data
public class NoteDto {
    private String content;

    public NoteDto(String content) {
        this.content = content;
    }

    public NoteDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NoteDto{" +
                "content='" + content + '\'' +
                '}';
    }
}
