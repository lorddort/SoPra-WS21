package de.unistuttgart.iste.sopraws20.api.RESTController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.unistuttgart.iste.sopraws20.api.services.CryptoCurrencyService;
import de.unistuttgart.iste.sopraws20.api.values.CryptoCurrency;
import de.unistuttgart.iste.sopraws20.api.values.CryptoIdName;

/**
 * Control action for CryptoCurrency(cc) using REST resources
 */
@RestController
public class CryptoCurrencyController {
	@Autowired
	private CryptoCurrencyService cryptoCurrencyService;

	/**
	 * Controller to get list of Strings with crypto IDs and their names
	 *
	 * @return crypto list
	 */
	@GetMapping("/cryptos/list")
	public List<CryptoIdName> getCryptoCurrencyNames() {
		return cryptoCurrencyService.getCryptoCurrencyNames();
	}

	/**
	 * Controller to get a number of crypto ID and name in descending order from
	 * market cap
	 *
	 * @param amount number of cryptos
	 * @return crypto list
	 */
	@GetMapping("/cryptos/list/{amount}")
	public List<CryptoIdName> getCryptoCurrencyNames(@PathVariable("amount") @Valid int amount) {
		return cryptoCurrencyService.getCryptoCurrencyNames(amount);

	}

	/**
	 * Controller to get cc by id
	 *
	 * @param id crypto ID
	 * @return crypto information
	 */
	// TODO is static ergo not yet in Service
	@GetMapping("cryptos/{id}")
	public static CryptoCurrency getCryptoCurrencyByName(@PathVariable String id) {
		return CryptoCurrencyService.getCryptoCurrencyByName(id);

	}

	/**
	 * Controller to get id and name of all loaded cc
	 *
	 * @return
	 */
	@GetMapping("cryptos/loaded")
	public List<CryptoIdName> getLoadedCryptoCurrencies() {
		return cryptoCurrencyService.getLoadedCryptoCurrencies();
	}

	/**
	 * Controller to get price chart in interval
	 * 
	 * @param id
	 * @param timeframe must be: today, 24h, week, month, last_month, year
	 * @return
	 */
	@GetMapping("cryptos/{id}/price/{timeframe}")
	public List<List<String>> getPriceChartByTimeframe(@PathVariable("id") String id,
			@PathVariable("timeframe") String timeframe) {
		return CryptoCurrencyService.getPriceChartByTimeframe(id, timeframe);
	}

	/**
	 * Controller to get volume chart in interval
	 * 
	 * @param id
	 * @param timeframe must be: today, 24h, week, month, last_month, year
	 * @return
	 */
	@GetMapping("cryptos/{id}/volume/{timeframe}")
	public List<List<String>> getVolumeChartByTimeframe(@PathVariable("id") String id,
			@PathVariable("timeframe") String timeframe) {
		return CryptoCurrencyService.getVolumeChartByTimeframe(id, timeframe);
	}

	/**
	 * Controller to get mc chart in interval
	 * 
	 * @param id
	 * @param timeframe must be: today, 24h, week, month, last_month, year
	 * @return
	 */
	@GetMapping("cryptos/{id}/market_cap/{timeframe}")
	public List<List<String>> getMarketCapChartByTimeframe(@PathVariable("id") String id,
			@PathVariable("timeframe") String timeframe) {
		return CryptoCurrencyService.getMarketCapChartByTimeframe(id, timeframe);
	}

	/**
	 * Controller to get logo url for crypto
	 *
	 * @param id crypto ID
	 * @return crypto logo
	 */
	@GetMapping("cryptos/{id}/logourl")
	public String setLogoUrl(@PathVariable("id") String id) {
		return cryptoCurrencyService.setLogoUrl(id);
	}

	/**
	 * Controller to adds cc with ID to preloading list and autofills information
	 * from coingecko
	 *
	 * @param id crypto ID
	 * @return crypto information
	 */
	@PostMapping("/cryptos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public CryptoCurrency addCryptoCurrency(@PathVariable("id") String id) {
		return cryptoCurrencyService.addCryptoCurrency(id);
	}

	/**
	 * Controller to update logo url for crypto
	 *
	 * @param id         crypto ID
	 * @param newLogoUrl new logo url
	 * @return
	 */
	@PutMapping("cryptos/{id}/logourl")
	public String editLogoUrl(@PathVariable("id") String id, String newLogoUrl) {
		return cryptoCurrencyService.editLogoUrl(id, newLogoUrl);
	}

}
