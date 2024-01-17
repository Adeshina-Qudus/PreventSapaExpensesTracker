package sapa.prevent.utils;

import sapa.prevent.data.models.User;
import sapa.prevent.dtos.RegisterRequest;
import static sapa.prevent.Validation.EmptyDetails.emptyDetails;
import static sapa.prevent.Validation.PasswordValidation.passwordValidation;

public class Mapper {

    public static User map(RegisterRequest registerRequest){
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setConfirmPassword(registerRequest.getConfirmPassword());
        emptyDetails(registerRequest);
        passwordValidation(registerRequest);
        return user;
    }

}
