package de.unistuttgart.iste.sopraws20.api.RESTController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import de.unistuttgart.iste.sopraws20.api.services.CryptoCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.litesoftwares.coingecko.exception.CoinGeckoApiException;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

import de.unistuttgart.iste.sopraws20.api.database.Importer;
import de.unistuttgart.iste.sopraws20.api.values.CryptoCurrency;
import de.unistuttgart.iste.sopraws20.api.values.CryptoIdName;

@RestController
public class CryptoCurrencyController {
	@Autowired
	private CryptoCurrencyService cryptoCurrencyService;

	// gets list of Strings with crypto IDs and their names
	@GetMapping("/cryptos/list")
	public List<CryptoIdName> getCryptoCurrencyNames() {
		return cryptoCurrencyService.getCryptoCurrencyNames();
	}

	// get a number of crypto ID and name in order market cap descending
	@GetMapping("/cryptos/list/{amount}")
	public List<CryptoIdName> getCryptoCurrencyNames(@PathVariable("amount") @Valid int amount) {
		return cryptoCurrencyService.getCryptoCurrencyNames(amount);

	}

	// adds cc with ID, autofills information from coingecko
	@PostMapping("/cryptos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public CryptoCurrency addCryptoCurrency(@PathVariable("id") String id) {
		return cryptoCurrencyService.addCryptoCurrency(id);
	}

	// get cc by id and is static ergo not yet in Service
	@GetMapping("cryptos/{id}")
	public static CryptoCurrency getCryptoCurrencyByName(@PathVariable String id) {
		return CryptoCurrencyService.getCryptoCurrencyByName(id);

	}

	// get id and name of all loaded cc
	@GetMapping("cryptos/loaded")
	public List<CryptoIdName> getLoadedCryptoCurrencies() {
		return cryptoCurrencyService.getLoadedCryptoCurrencies();
	}

	// get logo url for crypto
	@GetMapping("cryptos/{id}/logourl")
	public String setLogoUrl(@PathVariable("id") String id) {
		return cryptoCurrencyService.setLogoUrl(id);
	}

	// update logo url for crypto
	@PutMapping("cryptos/{id}/logourl")
	public String editLogoUrl(@PathVariable("id") String id, String newLogoUrl) {
		return cryptoCurrencyService.editLogoUrl(id, newLogoUrl);
	}

}
