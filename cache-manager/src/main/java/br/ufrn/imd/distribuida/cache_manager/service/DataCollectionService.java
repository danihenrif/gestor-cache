package br.ufrn.imd.distribuida.cache_manager.service;

import br.ufrn.imd.distribuida.cache_manager.model.DataCollection;
import br.ufrn.imd.distribuida.cache_manager.repository.DataCollectionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataCollectionService {

	private final DataCollectionRepository dataCollectionRepository;

	DataCollectionService(DataCollectionRepository dataCollectionRepository) {
		this.dataCollectionRepository = dataCollectionRepository;
	}


	public DataCollection create(DataCollection dataCollection) {
		return this.dataCollectionRepository.save(dataCollection);
	}

	public DataCollection findById(String id) {
		Optional<DataCollection> dataCollectionOptional = this.dataCollectionRepository.findById(id);
		if(dataCollectionOptional.isEmpty()) {
			throw new RuntimeException("Error: data collection with id " + id + " not found.");
		}
		return dataCollectionOptional.get();
	}

}
