package br.ufrn.imd.distribuida.cache_manager.controller;

import br.ufrn.imd.distribuida.cache_manager.model.DataCollection;
import br.ufrn.imd.distribuida.cache_manager.service.DataCollectionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cache")
public class DataCollectionController {
	private final DataCollectionService dataCollectionService;

	DataCollectionController(DataCollectionService dataCollectionService) {
		this.dataCollectionService = dataCollectionService;
	}

	@GetMapping("/{id}")
	public DataCollection findById(@PathVariable("id") String id) {
		return this.dataCollectionService.findById(id);
	}

	@PostMapping
	public DataCollection save(@RequestBody DataCollection dataCollection) {
		return this.dataCollectionService.create(dataCollection);
	}
}
