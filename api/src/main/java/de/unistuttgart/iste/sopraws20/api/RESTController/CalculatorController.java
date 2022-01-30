package de.unistuttgart.iste.sopraws20.api.RESTController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.unistuttgart.iste.sopraws20.api.services.CalculatorService;
import de.unistuttgart.iste.sopraws20.api.values.CorrelationCoefficient;

/**
 * Control action for Calculator using REST resources
 */
@RestController
public class CalculatorController {
	@Autowired
	private CalculatorService calculatorService;

	/**
	 * Controller for calculating correlation coefficient
	 *
	 * @param type correlation type
	 * @param id1  first selected currency
	 * @param id2  decond selected currency
	 * @return correlation coefficient
	 */
	@GetMapping("/calculator/crypto/corr")
	public CorrelationCoefficient getCryptoCorrelationCoefficient(String type, String id1, String id2) {
		return calculatorService.getCryptoCorrelationCoefficient(type, id1, id2);
	}

	@GetMapping("/calculator/crypto/corr/{interval}")
	public CorrelationCoefficient getCorrelationCoefficientByInterval(String type,
			@PathVariable("interval") String interval, String id1, String id2) {
		return calculatorService.getCorrelationCoefficientByInterval(type, interval, id1, id2);
	}
}
