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

/**
 * Class with Services for CryptoCurrency(cc)
 */
@Service
public class CryptoCurrencyService {
	private static Map<String, CryptoCurrency> cryptoCurrencies;
	// list of name and ID of cc
	private List<CryptoIdName> cryptoCurrencyNames;
	private List<CryptoIdName> loadedIdAndNames;

	/**
	 * Executed after startup
	 */
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

	/**
	 * Gets list of Strings with crypto IDs and their names
	 *
	 * @return crypto list
	 */
	public List<CryptoIdName> getCryptoCurrencyNames(){
		cryptoCurrencyNames = Importer.getCryptoCurrencyNamesAndIds();
		return cryptoCurrencyNames;
	}

	/**
	 * Get a number of crypto ID and name in descending order from market cap
	 *
	 * @param amount number of cryptos
	 * @return crypto list
	 */
	public List<CryptoIdName> getCryptoCurrencyNames(int amount) {
		cryptoCurrencyNames = Importer.getCryptoCurrencyNamesAndIds(amount);
		return cryptoCurrencyNames;
	}

	/**
	 * Get cc by id
	 *
	 * @param id crypto ID
	 * @return crypto information
	 */
	public static CryptoCurrency getCryptoCurrencyByName(String id) {
		return cryptoCurrencies.get(id);
	}

	/**
	 * Get id and name of all loaded cc
	 *
	 * @return
	 */
	public List<CryptoIdName> getLoadedCryptoCurrencies() {
		return loadedIdAndNames;
	}

	/**
	 * Get logo url for crypto
	 *
	 * @param id crypto ID
	 * @return crypto logo
	 */
	public String setLogoUrl(String id) {
		CryptoCurrency currCrypto = cryptoCurrencies.get(id);
		return currCrypto.getLogoUrl();
	}

	/**
	 * Adds cc with ID to preloading list and autofills information from coingecko
	 *
	 * @param id crypto ID
	 * @return crypto information
	 */
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

	/**
	 * Update logo url for crypto
	 *
	 * @param id crypto ID
	 * @param newLogoUrl new logo url
	 * @return
	 */
	public String editLogoUrl(String id, String newLogoUrl) {
		CryptoCurrency currCrypto = cryptoCurrencies.get(id);
		currCrypto.setLogoUrl(newLogoUrl);
		return newLogoUrl;
	}
}

