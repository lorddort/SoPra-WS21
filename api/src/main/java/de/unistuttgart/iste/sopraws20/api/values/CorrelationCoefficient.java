package de.unistuttgart.iste.sopraws20.api.values;

import javax.validation.constraints.NotNull;

/**
 * Class for correlation coefficient model
 */
public class CorrelationCoefficient {
	@NotNull
	public float correlationCoefficient;

	/**
	 * Constructor
	 *
	 * @param correlationCoefficient
	 */
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

