package br.ufrn.imd.distribuida.cache_manager.repository;

import br.ufrn.imd.distribuida.cache_manager.model.DataCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataCollectionRepository extends MongoRepository<DataCollection, String> {
}
