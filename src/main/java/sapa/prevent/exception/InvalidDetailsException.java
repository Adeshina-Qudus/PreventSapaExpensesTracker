package sapa.prevent.exception;

public class InvalidDetailsException extends ExpensesTrackerException {
    public InvalidDetailsException(String message) {
        super(message);
    }
}
