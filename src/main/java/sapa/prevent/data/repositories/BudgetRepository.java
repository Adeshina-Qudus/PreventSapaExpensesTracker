package sapa.prevent.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import sapa.prevent.data.models.Budget;

public interface BudgetRepository extends MongoRepository<Budget,String> {
    
}
