package sapa.prevent.exception;

public class AppLockedException extends ExpensesTrackerException {
    public AppLockedException(String message) {
        super(message);
    }
}
