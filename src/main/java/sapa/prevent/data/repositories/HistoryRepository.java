package sapa.prevent.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import sapa.prevent.data.models.History;

public interface HistoryRepository extends MongoRepository<History,String> {

}
