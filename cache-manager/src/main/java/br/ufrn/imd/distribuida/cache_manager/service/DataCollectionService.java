package br.ufrn.imd.distribuida.cache_manager.service;

import br.ufrn.imd.distribuida.cache_manager.dto.DataCollectionDTO;
import br.ufrn.imd.distribuida.cache_manager.exceptions.DataNotFoundException;
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


	public DataCollectionDTO create(DataCollection dataCollection) {
		DataCollection data =  this.dataCollectionRepository.save(dataCollection);
		DataCollectionDTO dataReturn = new DataCollectionDTO(data.getId());
		return dataReturn;
	}

	public DataCollection findById(String id) {
		Optional<DataCollection> dataCollectionOptional = this.dataCollectionRepository.findById(id);
		if(dataCollectionOptional.isEmpty()) {
			throw new DataNotFoundException("Error: data collection with id " + id + " not found.");
		}
		return dataCollectionOptional.get();
	}

}
