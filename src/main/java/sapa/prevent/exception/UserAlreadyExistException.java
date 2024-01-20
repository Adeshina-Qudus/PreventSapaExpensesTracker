package sapa.prevent.exception;

public class UserAlreadyExistException  extends ExpensesTrackerException{

    public UserAlreadyExistException(String message){
        super(message);
    }
}
