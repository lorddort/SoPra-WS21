package de.unistuttgart.iste.sopraws20.api.RESTController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

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

	private static Map<String, CryptoCurrency> cryptoCurrencies;
	// list of name and ID of cc
	private List<CryptoIdName> cryptoCurrencyNames;
	private List<CryptoIdName> loadedIdAndNames;

	// executed after startup
	@PostConstruct
	public void init() {
		cryptoCurrencies = new HashMap<String, CryptoCurrency>();
		cryptoCurrencyNames = new ArrayList<CryptoIdName>();
		loadedIdAndNames = new ArrayList<CryptoIdName>();
		CoinGeckoApiClientImpl coinGeckoApiClient = new CoinGeckoApiClientImpl();
		try {
			coinGeckoApiClient.ping();
		} catch (CoinGeckoApiException e) {
			// TODO
		}
	}

	// gets list of Strings with crypto IDs and their names
	@GetMapping("/cryptos/list")
	public List<CryptoIdName> getCryptoCurrencyNames() {
		cryptoCurrencyNames = Importer.getCryptoCurrencyNamesAndIds();

		return cryptoCurrencyNames;

	}

	// get a number of crypto ID and name in order market cap descending
	@GetMapping("/cryptos/list/{amount}")
	public List<CryptoIdName> getCryptoCurrencyNames(@PathVariable @Valid int amount) {
		cryptoCurrencyNames = Importer.getCryptoCurrencyNamesAndIds(amount);

		return cryptoCurrencyNames;

	}

	// adds cc with ID, autofills information from coingecko
	@PostMapping("/cryptos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public CryptoCurrency addCryptoCurrency(@PathVariable String id) {
		// check if crypto is already loaded
		for (CryptoIdName loaded : loadedIdAndNames) {
			if (loaded.getId().equals(id)) {
				return cryptoCurrencies.get(id);
			}
		}
		CryptoCurrency newCrypto = Importer.loadCrypto(id);
		cryptoCurrencies.put(newCrypto.getId(), newCrypto);
		CryptoIdName idAndName = new CryptoIdName(newCrypto.getId(), newCrypto.getName());
		loadedIdAndNames.add(idAndName);
		return newCrypto;

	}

	// get cc by id
	@GetMapping("cryptos/{id}")
	public static CryptoCurrency getCryptoCurrencyByName(@PathVariable("id") String id) {
		return cryptoCurrencies.get(id);

	}

	// get id and name of all loaded cc
	@GetMapping("cryptos/loaded")
	public List<CryptoIdName> getLoadedCryptoCurrencies() {
		return loadedIdAndNames;

	}

	// get logo url for crypto
	@GetMapping("cryptos/{id}/logourl")
	public String setLogoUrl(@PathVariable("id") String id) {
		CryptoCurrency currCrypto = cryptoCurrencies.get(id);
		return currCrypto.getLogoUrl();

	}

	// update logo url for crypto
	@PutMapping("cryptos/{id}/logourl")
	public String editLogoUrl(@PathVariable("id") String id, String newLogoUrl) {
		CryptoCurrency currCrypto = cryptoCurrencies.get(id);
		currCrypto.setLogoUrl(newLogoUrl);

		return newLogoUrl;

	}

}
