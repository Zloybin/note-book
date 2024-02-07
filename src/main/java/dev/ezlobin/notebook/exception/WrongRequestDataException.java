package dev.ezlobin.notebook.exception;

public class WrongRequestDataException extends RuntimeException{
    public WrongRequestDataException(String message) {
        super(message);
    }

    public WrongRequestDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
