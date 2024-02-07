package dev.ezlobin.notebook.exception;

public class NotebookMapperException extends WrongRequestDataException {

    public NotebookMapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotebookMapperException(String message) {
        super(message);
    }
}
