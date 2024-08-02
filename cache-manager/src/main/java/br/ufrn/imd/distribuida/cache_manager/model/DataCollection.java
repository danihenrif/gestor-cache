package br.ufrn.imd.distribuida.cache_manager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection = "data_collection")
public class DataCollection {
	@Id
	@NotBlank(message = "Id is required")
	private String id;
	@NotBlank(message = "Database is required")
	private String database;
	@NotNull(message = "data is required")
	private Object data;

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
