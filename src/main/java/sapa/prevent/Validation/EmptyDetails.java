package sapa.prevent.Validation;

import sapa.prevent.dtos.RegisterRequest;
import sapa.prevent.exception.EmptyDetailsException;

public class EmptyDetails {
    public static void emptyDetails(RegisterRequest registerRequest) {
        if (registerRequest.getEmail().isEmpty() || registerRequest.getPassword().isEmpty() ||
                registerRequest.getConfirmPassword().isEmpty()) throw new EmptyDetailsException();
    }
}
