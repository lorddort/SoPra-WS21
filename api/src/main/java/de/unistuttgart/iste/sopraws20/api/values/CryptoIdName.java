package de.unistuttgart.iste.sopraws20.api.values;

import javax.validation.constraints.NotNull;

/**
 * Class for crypto models with ids and names
 */
public class CryptoIdName {

	@NotNull
	private String id;
	@NotNull
	private String name;

	public CryptoIdName(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
