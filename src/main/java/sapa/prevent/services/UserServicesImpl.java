package sapa.prevent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapa.prevent.data.models.User;
import sapa.prevent.data.repositories.UserRepository;
import sapa.prevent.dtos.RegisterRequest;
import sapa.prevent.exception.UserAlreadyExistException;

import static sapa.prevent.utils.Mapper.map;

@Service
public class UserServicesImpl implements  UserServices{
    @Autowired
    private UserRepository userRepository;
    @Override
    public void register(RegisterRequest registerRequest) {
        if (UserExist(registerRequest.getEmail())) throw new UserAlreadyExistException(
                registerRequest.getEmail()+" Already Exist");
        User user = map(registerRequest);
        userRepository.save(user);
    }

    private boolean UserExist(String email) {
        User foundUser = userRepository.findByEmail(email);
        return foundUser != null;
    }
}
