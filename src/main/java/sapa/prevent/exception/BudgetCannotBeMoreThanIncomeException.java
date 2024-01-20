package sapa.prevent.exception;

public class BudgetCannotBeMoreThanIncomeException extends ExpensesTrackerException {
    public BudgetCannotBeMoreThanIncomeException(String message) {
        super(message);
    }
}
