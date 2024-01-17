package sapa.prevent.exception;

public class EmptyDetailsException extends RuntimeException {
    public EmptyDetailsException(){
        super("Details Cannot Be Empty");
    }
}
