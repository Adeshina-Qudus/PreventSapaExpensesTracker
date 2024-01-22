package sapa.prevent.exception;

public class IsExceedBudgetLimitException extends ExpensesTrackerException {
    public IsExceedBudgetLimitException(String message) {
        super(message);
    }
}
