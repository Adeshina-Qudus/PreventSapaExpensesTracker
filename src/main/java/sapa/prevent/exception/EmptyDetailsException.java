package sapa.prevent.exception;

public class EmptyDetailsException extends ExpensesTrackerException {
    public EmptyDetailsException(){
        super("Details Cannot Be Empty");
    }
}
