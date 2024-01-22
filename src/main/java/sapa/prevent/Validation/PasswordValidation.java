package sapa.prevent.Validation;

import sapa.prevent.dtos.request.RegistrationRequest;
import sapa.prevent.exception.PasswordDoesNotMatchException;

public class PasswordValidation {

    public static void passwordValidation(RegistrationRequest registerRequest) {
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) throw new
                PasswordDoesNotMatchException();
    }
}
