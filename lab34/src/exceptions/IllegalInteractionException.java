package exceptions;

public class IllegalInteractionException extends RuntimeException {

    public IllegalInteractionException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "[НЕКОРРЕКТНОЕ ВЗАИМОДЕЙСТВИЕ] " + super.getMessage();
    }
}