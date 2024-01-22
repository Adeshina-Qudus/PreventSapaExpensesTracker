package sapa.prevent.dtos.request;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String email;
    private String password;
    private String confirmPassword;
}
