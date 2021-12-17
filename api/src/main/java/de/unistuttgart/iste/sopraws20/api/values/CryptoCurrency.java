package de.unistuttgart.iste.sopraws20.api.values;

import javax.validation.constraints.NotNull;

import com.litesoftwares.coingecko.domain.Coins.MarketChart;

public class CryptoCurrency extends HistoricalValue {

	@NotNull
	private String logoUrl;
	@NotNull
	private MarketChart minutelyChart;
	@NotNull
	private MarketChart hourlyChart;
	@NotNull
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

	public MarketChart getHourlyChart() {
		return hourlyChart;
	}

	public MarketChart getDailyChart() {
		return dailyChart;
	}

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
