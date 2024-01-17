package sapa.prevent.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sapa.prevent.data.repositories.UserRepository;
import sapa.prevent.dtos.RegisterRequest;
import sapa.prevent.exception.EmptyDetailsException;
import sapa.prevent.exception.UserAlreadyExistException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServicesImplTest {

    @Autowired
    private UserServices userServices;
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void doThisAfterEachTest(){
        userRepository.deleteAll();
    }

    @Test
    public void userCanRegisterAndUserIsOne(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.register(registerRequest);
        assertEquals(1,userRepository.count());
    }
    @Test
    public void userRegisterWithAnEmptyEmailTrowException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        assertThrows(EmptyDetailsException.class , ()-> userServices.register(registerRequest));
    }
    @Test
    public void userRegisterWithAnEmptyPasswordTrowException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("");
        registerRequest.setConfirmPassword("Iniestajnr");
        assertThrows(EmptyDetailsException.class , ()-> userServices.register(registerRequest));
    }
    @Test
    public void userRegisterWithOneEmailTwiceTrowException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.register(registerRequest);
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("qudus");
        registerRequest.setConfirmPassword("qudus");
        assertThrows(UserAlreadyExistException.class , ()-> userServices.register(registerRequest));
    }

}
