package demo.exception;

public class DivisionException extends RuntimeException {

    private final int input;
    public DivisionException (String message, int input) {
        super (message);
        this.input = input;
    }
}
