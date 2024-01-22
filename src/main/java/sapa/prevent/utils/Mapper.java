package sapa.prevent.utils;

import sapa.prevent.data.models.User;
import sapa.prevent.dtos.request.RegistrationRequest;
import static sapa.prevent.Validation.EmptyDetails.emptyDetails;
import static sapa.prevent.Validation.PasswordValidation.passwordValidation;

public class Mapper {

    public static User map(RegistrationRequest registerRequest){
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        emptyDetails(registerRequest);
        passwordValidation(registerRequest);
        return user;
    }

}
