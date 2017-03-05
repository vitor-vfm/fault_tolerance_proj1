public class FailureException extends RuntimeException {
    static final long serialVersionUID = 42L;

    public FailureException(String message) {
        super(message);
    }
}
