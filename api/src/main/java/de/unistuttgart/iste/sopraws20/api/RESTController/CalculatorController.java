package de.unistuttgart.iste.sopraws20.api.RESTController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.unistuttgart.iste.sopraws20.api.statistic.Calculator;
import de.unistuttgart.iste.sopraws20.api.values.CryptoCurrency;

@RestController
public class CalculatorController {
	/*
	 * gets correlation coefficient of last year of specified crypto by id. For
	 * correlation of at least one non crypto asset, use different method
	 * 
	 * @param type use volume or marketcap, otherwise price correlation will be
	 * returned
	 * 
	 * @param id1 use id of loaded crypto, otherwise -2 will be returned
	 * 
	 * @param id2 use id of loaded crypto, otherwise -2 will be returned
	 */
	@GetMapping("/calculator/crypto/corr/year")
	public float getCryptoCorrelationCoefficient(String type, String id1, String id2) {
		CryptoCurrency crypto1;
		CryptoCurrency crypto2;
		try {
			crypto1 = CryptoCurrencyController.getCryptoCurrencyByName(id1);
			crypto2 = CryptoCurrencyController.getCryptoCurrencyByName(id2);
			float[] crypto1Arr;
			float[] crypto2Arr;
			if (type.equals("marketcap")) {
				crypto1Arr = Calculator.cryptoDataToArray(crypto1.getDailyChart().getMarketCaps());
				crypto2Arr = Calculator.cryptoDataToArray(crypto2.getDailyChart().getMarketCaps());
			} else if (type.equals("volume")) {
				crypto1Arr = Calculator.cryptoDataToArray(crypto1.getDailyChart().getTotalVolumes());
				crypto2Arr = Calculator.cryptoDataToArray(crypto2.getDailyChart().getTotalVolumes());
			} else {
				crypto1Arr = Calculator.cryptoDataToArray(crypto1.getDailyChart().getPrices());
				crypto2Arr = Calculator.cryptoDataToArray(crypto2.getDailyChart().getPrices());
			}
			return Calculator.cutInputCorrelationCoefficient(crypto1Arr, crypto2Arr);
		} catch (NullPointerException e) {
			return -2;
		}
	}
}
