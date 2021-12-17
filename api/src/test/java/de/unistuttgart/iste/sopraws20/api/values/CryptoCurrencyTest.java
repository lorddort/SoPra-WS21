package de.unistuttgart.iste.sopraws20.api.values;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.litesoftwares.coingecko.domain.Coins.MarketChart;

class CryptoCurrencyTest {

	static MarketChart testChart;

	@BeforeAll
	public static void init() {
		testChart = new MarketChart();
		testChart.setMarketCaps(getChart());
		testChart.setPrices(getChart());
		testChart.setTotalVolumes(getChart());
	}

	private static List<List<String>> getChart() {
		List<List<String>> chart = new ArrayList();
		for (int i = 0; i < 4; i++) {
			List<String> price = new ArrayList();
			price.add(i + "");
			price.add((1 + i) + "");
			chart.add(price);
		}
		return chart;
	}

	@Test
	void makeCrypto() {
		CryptoCurrency crypto = new CryptoCurrency("btc");
		crypto.setDescription("a curr");
		crypto.setLogoUrl("btc.png");
		crypto.setName("bitcoin");
		crypto.setDailyChart(testChart);
		crypto.setHourlyChart(testChart);
		crypto.setMinutelyChart(testChart);
		crypto.setStandardPriceChart(testChart);

		Assertions.assertEquals(crypto.getId(), "btc");
		Assertions.assertEquals(crypto.getDescription(), "a curr");
		Assertions.assertEquals(crypto.getName(), "bitcoin");
		Assertions.assertEquals(crypto.getDailyChart(), crypto.getHourlyChart());
		Assertions.assertEquals(crypto.getDailyChart(), crypto.getMinutelyChart());
		Assertions.assertTrue(crypto.getMinutelyChart().getPrices().get(2).contains("2"));
		Assertions.assertTrue(crypto.getMinutelyChart().getPrices().get(2).contains("3"));
	}

}
