package exceptions;

public class AlreadyDestroyedException extends Exception {
    public AlreadyDestroyedException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "[ОБЪЕКТ УЖЕ РАЗРУШЕН] " + super.getMessage();
    }
}
