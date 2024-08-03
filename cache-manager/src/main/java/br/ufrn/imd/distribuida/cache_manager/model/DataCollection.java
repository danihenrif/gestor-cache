package br.ufrn.imd.distribuida.cache_manager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "data_collection")
public class DataCollection {
	@Id
	@NotBlank(message = "Id is required")
	private String id;
	@NotBlank(message = "Database is required")
	private String database;
	@NotBlank(message = "data is required")
	private String data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
