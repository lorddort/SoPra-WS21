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

@RestController
public class CryptoCurrencyController {

	private List<CryptoCurrency> cryptoCurrencies;
	// list of name or ticker symbol of cc
	private List<String> cryptoCurrencyNames;

	@PostConstruct
	public void init() {
		CryptoCurrency bitcoin = new CryptoCurrency("bitcoin", "first Cryptocurrency");
		CoinGeckoApiClientImpl coinGeckoApiClient = new CoinGeckoApiClientImpl();
		try {
			coinGeckoApiClient.ping();
		} catch (CoinGeckoApiException e) {
			// TODO

		}
		// TODO: load list of cc
	}

	// gets list of Strings with crypto names
	@GetMapping("/cryptos/list")
	public List<String> getCryptoCurrencyNames() {
		cryptoCurrencyNames = Importer.getCryptoCurrencyNames();

		return cryptoCurrencyNames;
		// TODO
	}

	// adds cc with name, autofills information
	@PostMapping("/cryptos")
	public CryptoCurrency addCryptoCurrency(@Valid @RequestBody String name) {
		// TODO
		return null;

	}

	@GetMapping("cryptos/{name}")
	public CryptoCurrency getCryptoCurrencyByName(@PathVariable("name") String name) {
		// TODO
		return null;

	}

	@PostMapping("cryptos/{name}/{logourl}")
	public String setLogoUrl(@PathVariable("name") String name, @PathVariable("logourl") String logoUrl) {
		// TODO
		return logoUrl;

	}

	@PutMapping("cryptos/{name}/{logourl}")
	public String editLogoUrl(@PathVariable("name") String name, @PathVariable("logourl") String logoUrl) {
		// TODO
		return logoUrl;

	}

}
