package de.unistuttgart.iste.sopraws20.api.RESTController;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.litesoftwares.coingecko.exception.CoinGeckoApiException;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

import de.unistuttgart.iste.sopraws20.api.database.Importer;
import de.unistuttgart.iste.sopraws20.api.values.CryptoCurrency;
import de.unistuttgart.iste.sopraws20.api.values.CryptoIdName;

@RestController
public class CryptoCurrencyController {

	private List<CryptoCurrency> cryptoCurrencies;
	// list of name and ID of cc
	private List<CryptoIdName> cryptoCurrencyNames;

	@PostConstruct
	public void init() {
		CryptoCurrency bitcoin = new CryptoCurrency("bitcoin");
		CoinGeckoApiClientImpl coinGeckoApiClient = new CoinGeckoApiClientImpl();
		try {
			coinGeckoApiClient.ping();
		} catch (CoinGeckoApiException e) {
			// TODO

		}
		// TODO: load list of cc
	}

	// gets list of Strings with crypto IDs and their names
	@GetMapping("/cryptos/list")
	public List<CryptoIdName> getCryptoCurrencyNames() {
		cryptoCurrencyNames = Importer.getCryptoCurrencyNamesAndIds();

		return cryptoCurrencyNames;
		// TODO
	}

	@GetMapping("/cryptos/list/{amount}")
	public List<CryptoIdName> getCryptoCurrencyNames(@Valid int amount) {
		cryptoCurrencyNames = Importer.getCryptoCurrencyNamesAndIds(amount);

		return cryptoCurrencyNames;
		// TODO
	}

	// adds cc with name, autofills information
	@PostMapping("/cryptos")
	public CryptoCurrency addCryptoCurrency(@Valid @RequestBody String id) {
		// TODO
		return null;

	}

	@GetMapping("cryptos/{id}")
	public CryptoCurrency getCryptoCurrencyByName(@PathVariable("id") String id) {
		// TODO
		return null;

	}

	@GetMapping("cryptos/loaded")
	public List<CryptoCurrency> getLoadedCryptoCurrencies() {
		return cryptoCurrencies;
		// TODO

	}

	@PostMapping("cryptos/{id}/{logourl}")
	public String setLogoUrl(@PathVariable("id") String id, @PathVariable("logourl") String logoUrl) {
		// TODO
		return logoUrl;

	}

	@PutMapping("cryptos/{id}/{logourl}")
	public String editLogoUrl(@PathVariable("id") String id, @PathVariable("logourl") String logoUrl) {
		// TODO
		return logoUrl;

	}

}
