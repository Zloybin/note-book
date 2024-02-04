package dev.ezlobin.notebook.exception;

public class NoteMapperException extends RuntimeException{

    public NoteMapperException(String message) {
        super(message);
    }

    public NoteMapperException(String message, Throwable cause) {
        super(message, cause);
    }
}
