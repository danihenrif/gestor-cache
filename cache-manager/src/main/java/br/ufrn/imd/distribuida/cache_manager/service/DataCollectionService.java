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
	private final CacheTrackingService cacheTrackingService;

	DataCollectionService(DataCollectionRepository dataCollectionRepository, CacheTrackingService cacheTrackingService) {
		this.dataCollectionRepository = dataCollectionRepository;
		this.cacheTrackingService = cacheTrackingService;
	}


	public DataCollectionDTO create(DataCollection dataCollection) {
		DataCollection data =  this.dataCollectionRepository.save(dataCollection);
		DataCollectionDTO dataReturn = new DataCollectionDTO(data.getId());
		return dataReturn;
	}


	public void deleteById(String id) {
		this.dataCollectionRepository.deleteById(id);
	}

	public DataCollection findById(String id, String token) {
		Optional<DataCollection> dataCollectionOptional = this.dataCollectionRepository.findById(id);

		if(dataCollectionOptional.isEmpty()) {
			throw new DataNotFoundException("Error: data collection with id " + id + " not found.");
		}

		boolean isTracked = cacheTrackingService.trackAccess(token);
		if (isTracked && cacheTrackingService.allDashboardsAccessed()) {
			this.deleteById(id);
			cacheTrackingService.clearCache();
		}

		return dataCollectionOptional.get();
	}

}
