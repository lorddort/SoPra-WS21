package de.unistuttgart.iste.sopraws20.api.services;

import com.litesoftwares.coingecko.exception.CoinGeckoApiException;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;
import de.unistuttgart.iste.sopraws20.api.database.Importer;
import de.unistuttgart.iste.sopraws20.api.values.CryptoCurrency;
import de.unistuttgart.iste.sopraws20.api.values.CryptoIdName;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CryptoCurrencyService {
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

	public List<CryptoIdName> getCryptoCurrencyNames(){
		cryptoCurrencyNames = Importer.getCryptoCurrencyNamesAndIds();
		return cryptoCurrencyNames;
	}

	public List<CryptoIdName> getCryptoCurrencyNames(int amount) {
		cryptoCurrencyNames = Importer.getCryptoCurrencyNamesAndIds(amount);
		return cryptoCurrencyNames;
	}

	public CryptoCurrency addCryptoCurrency(String id) {
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

	public static CryptoCurrency getCryptoCurrencyByName(String id) {
		return cryptoCurrencies.get(id);
	}

	public List<CryptoIdName> getLoadedCryptoCurrencies() {
		return loadedIdAndNames;
	}

	public String setLogoUrl(String id) {
		CryptoCurrency currCrypto = cryptoCurrencies.get(id);
		return currCrypto.getLogoUrl();
	}

	public String editLogoUrl(String id, String newLogoUrl) {
		CryptoCurrency currCrypto = cryptoCurrencies.get(id);
		currCrypto.setLogoUrl(newLogoUrl);
		return newLogoUrl;
	}
}

