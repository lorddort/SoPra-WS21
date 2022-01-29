package de.unistuttgart.iste.sopraws20.api.services;

import org.springframework.stereotype.Service;

import de.unistuttgart.iste.sopraws20.api.RESTController.CryptoCurrencyController;
import de.unistuttgart.iste.sopraws20.api.statistic.Calculator;
import de.unistuttgart.iste.sopraws20.api.values.CorrelationCoefficient;
import de.unistuttgart.iste.sopraws20.api.values.CryptoCurrency;

/**
 * Class with Services for Calculator
 */
@Service
public class CalculatorService {

	/**
	 * Gets correlation coefficient of last year of specified crypto by id. For
	 * correlation of at least one non crypto asset, use different method
	 *
	 * @param type use volume or marketcap, otherwise price correlation will be
	 *             returned
	 *
	 * @param id1  use id of loaded crypto, otherwise -2 will be returned
	 *
	 * @param id2  use id of loaded crypto, otherwise -2 will be returned
	 * @return correlation coefficient or -2 for invalid ids
	 */
	public CorrelationCoefficient getCryptoCorrelationCoefficient(String type, String id1, String id2) {
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
			return new CorrelationCoefficient(Calculator.cutInputCorrelationCoefficient(crypto1Arr, crypto2Arr));
		} catch (NullPointerException e) {
			return new CorrelationCoefficient(-2);
		}
	}

	/**
	 * Gets correlation coefficient of last year of specified crypto by id. For
	 * correlation of at least one non crypto asset, use different method
	 *
	 * @param type     use volume or marketcap, otherwise price correlation will be
	 *                 returned
	 *
	 * @param id1      use id of loaded crypto, otherwise -2 will be returned
	 *
	 * @param id2      use id of loaded crypto, otherwise -2 will be returned
	 * @param interval today, 24h, week, month, last_month, year
	 * @return correlation coefficient or -2 for invalid ids
	 */
	public CorrelationCoefficient getCorrelationCoefficientByInterval(String type, String interval, String id1,
			String id2) {
		CryptoCurrency crypto1;
		CryptoCurrency crypto2;
		try {
			crypto1 = CryptoCurrencyController.getCryptoCurrencyByName(id1);
			crypto2 = CryptoCurrencyController.getCryptoCurrencyByName(id2);
			float[] crypto1Arr;
			float[] crypto2Arr;
			if (type.equals("marketcap")) {
				crypto1Arr = Calculator.cryptoDataToArray(crypto1.getMarketCapChartByTimeframe(interval));
				crypto2Arr = Calculator.cryptoDataToArray(crypto2.getMarketCapChartByTimeframe(interval));
			} else if (type.equals("volume")) {
				crypto1Arr = Calculator.cryptoDataToArray(crypto1.getVolumeChartByTimeframe(interval));
				crypto2Arr = Calculator.cryptoDataToArray(crypto2.getVolumeChartByTimeframe(interval));
			} else {
				crypto1Arr = Calculator.cryptoDataToArray(crypto1.getPriceChartByTimeframe(interval));
				crypto2Arr = Calculator.cryptoDataToArray(crypto2.getPriceChartByTimeframe(interval));
			}
			return new CorrelationCoefficient(Calculator.cutInputCorrelationCoefficient(crypto1Arr, crypto2Arr));
		} catch (NullPointerException e) {
			return new CorrelationCoefficient(-2);
		}
	}

}
