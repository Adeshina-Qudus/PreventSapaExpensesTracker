package sapa.prevent.exception;

public class UserNotFoundException extends ExpensesTrackerException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
