package de.unistuttgart.iste.sopraws20.api.values;

import javax.validation.constraints.NotNull;

public class CorrelationCoefficient {
	@NotNull
	public float correlationCoefficient;

	public CorrelationCoefficient(float correlationCoefficient){
		this.correlationCoefficient = correlationCoefficient;
	}

	public float getCorrelationCoefficient() {
		return correlationCoefficient;
	}

	public void setCorrelationCoefficient(float correlationCoefficient) {
		this.correlationCoefficient = correlationCoefficient;
	}
}

