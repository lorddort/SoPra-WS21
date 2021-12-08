package de.unistuttgart.iste.sopraws20.api.RESTController;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.RestController;

import com.litesoftwares.coingecko.exception.CoinGeckoApiException;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

import de.unistuttgart.iste.sopraws20.api.values.CryptoCurrency;

@RestController
public class CryptoCurrencyController {

	private List<CryptoCurrency> cryptoCurrencies;

	@PostConstruct
	public void init() {
		CryptoCurrency bitcoin = new CryptoCurrency("bitcoin", "first Cryptocurrency");
		CoinGeckoApiClientImpl coinGeckoApiClient = new CoinGeckoApiClientImpl();
		try {
			coinGeckoApiClient.ping();
		} catch (CoinGeckoApiException e) {
			// TODO

		}
	}

}
