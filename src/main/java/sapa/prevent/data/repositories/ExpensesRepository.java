package sapa.prevent.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import sapa.prevent.data.models.Expenses;

public interface ExpensesRepository extends MongoRepository<Expenses,String> {
}
