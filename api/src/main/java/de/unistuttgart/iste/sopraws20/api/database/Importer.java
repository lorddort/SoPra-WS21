package de.unistuttgart.iste.sopraws20.api.database;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.domain.Coins.CoinFullData;
import com.litesoftwares.coingecko.domain.Coins.CoinList;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

import de.unistuttgart.iste.sopraws20.api.values.CryptoCurrency;
import de.unistuttgart.iste.sopraws20.api.values.CryptoIdName;

/*
 * will be implemented further if more than one API is being used
 * Standard format for Crypto-Data will be same as CoinGecko-API
 */
public final class Importer {

	static CoinGeckoApiClient client = new CoinGeckoApiClientImpl();
	static List<CryptoIdName> coinNames = new LinkedList<CryptoIdName>();

	public static List<CryptoIdName> getCryptoCurrencyNamesAndIds() {
		// load list of coins
		if (coinNames.isEmpty()) {
			List<CoinList> coinLists = client.getCoinList();
			coinNames = new LinkedList<CryptoIdName>();
			String currName;
			String currId;
			for (CoinList coinList : coinLists) {
				currName = coinList.getName();
				currId = coinList.getId();
				coinNames.add(new CryptoIdName(currId, currName));
			}
		}
		return coinNames;
	}

	public static List<CryptoIdName> getCryptoCurrencyNamesAndIds(@Size(max = 250) int amount) {
		// load list of coins
		if (coinNames.isEmpty()) {
			List<CoinMarkets> coinMarkets = client.getCoinMarkets("eur", "", "market_cap_desc", amount, 1, false,
					"24h");
			coinNames = new LinkedList<CryptoIdName>();
			String currName;
			String currId;
			for (CoinMarkets coinMarket : coinMarkets) {
				currName = coinMarket.getName();
				currId = coinMarket.getId();
				coinNames.add(new CryptoIdName(currId, currName));
			}
		}
		return coinNames;
	}

	public static CryptoCurrency loadCrypto(@Valid String id) {

		CoinFullData cryptoBroadData = client.getCoinById(id);
		CryptoCurrency newCrypto = new CryptoCurrency(id);
		newCrypto.setName(cryptoBroadData.getName());
		// option to get thumb and small image as well
		newCrypto.setLogoUrl(cryptoBroadData.getImage().getLarge());
		newCrypto.setDescription(cryptoBroadData.getDescription().get("en"));

		// 1825 days are 5 years
		newCrypto.setDailyChart(client.getCoinMarketChartById(id, "eur", 365));
		newCrypto.setHourlyChart(client.getCoinMarketChartById(id, "eur", 90));
		newCrypto.setMinutelyChart(client.getCoinMarketChartById(id, "eur", 1));
		newCrypto.setStandardPriceChart(newCrypto.getDailyChart());
		return newCrypto;
	}
}
