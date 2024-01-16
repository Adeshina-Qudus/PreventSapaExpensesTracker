package sapa.prevent.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import sapa.prevent.data.models.Income;

public interface IncomeRepository extends MongoRepository<Income,String> {

}
