package sapa.prevent.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String confirmPassword;
}
