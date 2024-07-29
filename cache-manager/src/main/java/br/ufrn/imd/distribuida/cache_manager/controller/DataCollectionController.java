package br.ufrn.imd.distribuida.cache_manager.controller;

import br.ufrn.imd.distribuida.cache_manager.dto.DataCollectionDTO;
import br.ufrn.imd.distribuida.cache_manager.exceptions.DataNotFoundException;
import br.ufrn.imd.distribuida.cache_manager.exceptions.InvalidRequestException;
import br.ufrn.imd.distribuida.cache_manager.model.DataCollection;
import br.ufrn.imd.distribuida.cache_manager.service.DataCollectionService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class DataCollectionController {
    private final DataCollectionService dataCollectionService;

    DataCollectionController(DataCollectionService dataCollectionService) {
        this.dataCollectionService = dataCollectionService;
    }

    @GetMapping("/cache/{id}")
    public ResponseEntity<Object> findById(
            @PathVariable("id") String id,
            @RequestHeader(value = "X-Application-Token", required = true) String token) {

        if (!"dashboards:532452345345".equals(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        try {
            DataCollection dataCollection = this.dataCollectionService.findById(id);
            if (dataCollection != null) {
                return ResponseEntity.ok(dataCollection);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/cache")
    public ResponseEntity<DataCollectionDTO> save(@Valid @RequestBody DataCollection dataCollection) {
        try {
            DataCollectionDTO dataCollectionDTO = this.dataCollectionService.create(dataCollection);
            return ResponseEntity.status(HttpStatus.CREATED).body(dataCollectionDTO);
        } catch (InvalidRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
