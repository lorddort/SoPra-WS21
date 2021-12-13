package de.unistuttgart.iste.sopraws20.api.values;

import com.litesoftwares.coingecko.domain.Coins.MarketChart;

public class CryptoCurrency extends HistoricalValue {

	// @NotNull
	private String logoUrl;
	// @NotNull
	private MarketChart minutelyChart;
	// @NotNull
	private MarketChart hourlyChart;
	// @NotNull
	private MarketChart dailyChart;

	public CryptoCurrency(String id) {
		super(id);
		logoUrl = "";
		minutelyChart = new MarketChart();
		hourlyChart = new MarketChart();
		dailyChart = new MarketChart();

	}

	public MarketChart getMinutelyChart() {
		return minutelyChart;
	}

//	public List<List<String>> getMinutelyPrices() {
//		return minutelyChart.getPrices();
//	}
//
//	public List<List<String>> getMinutelyMarketCaps() {
//		return minutelyChart.getMarketCaps();
//	}
//
//	public List<List<String>> getMinutelyVolumes() {
//		return minutelyChart.getTotalVolumes();
//	}

	public MarketChart getHourlyChart() {
		return hourlyChart;
	}

//	public List<List<String>> getHourlyPrices() {
//		return hourlyChart.getPrices();
//	}
//
//	public List<List<String>> getHourlyMarketCaps() {
//		return hourlyChart.getMarketCaps();
//	}
//
//	public List<List<String>> getHourlyVolumes() {
//		return hourlyChart.getTotalVolumes();
//	}

	public MarketChart getDailyChart() {
		return dailyChart;
	}

//	public List<List<String>> getDailyPrices() {
//		return dailyChart.getPrices();
//	}
//
//	public List<List<String>> getDailyMarketCaps() {
//		return dailyChart.getMarketCaps();
//	}
//
//	public List<List<String>> getDailyVolumes() {
//		return dailyChart.getPrices();
//	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public void setMinutelyChart(MarketChart minutelyChart) {
		this.minutelyChart = minutelyChart;
	}

	public void setHourlyChart(MarketChart hourlyChart) {
		this.hourlyChart = hourlyChart;
	}

	public void setDailyChart(MarketChart dailyChart) {
		this.dailyChart = dailyChart;
	}

}
