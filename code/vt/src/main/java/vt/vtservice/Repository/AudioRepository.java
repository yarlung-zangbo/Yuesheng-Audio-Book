package vt.vtservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vt.vtservice.Entity.Aduio;

public interface AudioRepository extends MongoRepository<Aduio,Integer> {

}
