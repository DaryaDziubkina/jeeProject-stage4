package by.gsy.epamProject.model.exeption;

public class ValidationException extends RuntimeException {
    String value;

    public ValidationException(String value) {
        this.value = value;
    }

    public ValidationException(String value, Throwable cause) {
        this.value = value;
    }
}
