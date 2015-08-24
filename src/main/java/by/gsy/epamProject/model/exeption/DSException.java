package by.gsy.epamProject.model.exeption;

public class DSException extends RuntimeException {
    private String message;

    public DSException() {

    }

    public DSException(String message) {
        this.message = message;
    }

    public DSException(String message, Throwable cause) {
        super(cause);
        this.message = message;
    }

//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
//        builder.append('{');
//        builder.append("message='").append(message);
//        if (super.getCause() != null) {
//            builder.append("; cause = ").append(super.getCause());
//        }
//        builder.append('}');
//        return builder.toString();
//
//    }
}
