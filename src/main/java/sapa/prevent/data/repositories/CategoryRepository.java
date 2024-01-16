package sapa.prevent.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import sapa.prevent.data.models.Category;

public interface CategoryRepository extends MongoRepository<Category,String> {
}
