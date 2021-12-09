package de.unistuttgart.iste.sopraws20.api.database;

import java.util.LinkedList;
import java.util.List;

import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.domain.Coins.CoinList;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

/*
 * will be implemented further if more than one API is being used
 * Standard format for Crypto-Data will be same as CoinGecko-API
 */
public final class Importer {

	static CoinGeckoApiClient client = new CoinGeckoApiClientImpl();

	public static List<String> getCryptoCurrencyNames() {
		// load list of coins
		List<CoinList> coinLists = client.getCoinList();
		List<String> coinNames = new LinkedList<String>();
		for (CoinList coinList : coinLists) {
			coinNames.add(coinList.getName());
		}
		return coinNames;
	}
}
