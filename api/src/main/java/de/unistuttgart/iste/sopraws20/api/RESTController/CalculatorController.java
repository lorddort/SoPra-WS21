package de.unistuttgart.iste.sopraws20.api.RESTController;

import de.unistuttgart.iste.sopraws20.api.services.CalculatorService;
import de.unistuttgart.iste.sopraws20.api.values.CorrelationCoefficient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.unistuttgart.iste.sopraws20.api.statistic.Calculator;
import de.unistuttgart.iste.sopraws20.api.values.CryptoCurrency;

@RestController
public class CalculatorController {
	@Autowired
	private CalculatorService calculatorService;

	@GetMapping("/calculator/crypto/corr/year")
	public CorrelationCoefficient getCryptoCorrelationCoefficient(String type, String id1, String id2) {
		return calculatorService.getCryptoCorrelationCoefficient(type, id1,id2);
	}
}
