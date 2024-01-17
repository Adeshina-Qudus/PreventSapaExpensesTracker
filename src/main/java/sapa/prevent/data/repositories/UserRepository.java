package sapa.prevent.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import sapa.prevent.data.models.User;

public interface UserRepository extends MongoRepository<User,String> {

    User findByEmail(String email);
}
