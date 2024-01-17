package sapa.prevent.exception;

public class PasswordDoesNotMatchException extends RuntimeException {
    public PasswordDoesNotMatchException(){
        super("Password doesn't match");
    }
}
