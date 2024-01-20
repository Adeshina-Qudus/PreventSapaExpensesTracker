package sapa.prevent.exception;

public class PasswordDoesNotMatchException extends ExpensesTrackerException {
    public PasswordDoesNotMatchException(){
        super("Password doesn't match");
    }
}
