package sapa.prevent.Validation;

import sapa.prevent.dtos.request.RegisterRequest;
import sapa.prevent.exception.PasswordDoesNotMatchException;

public class PasswordValidation {

    public static void passwordValidation(RegisterRequest registerRequest) {
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) throw new
                PasswordDoesNotMatchException();
    }
}
