package pl.ogorzalek.s27447_bank.exception;

public class InvalidAccountDataException extends RuntimeException {
    public InvalidAccountDataException(String message) {
        super(message);
    }
}
