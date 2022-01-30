package de.unistuttgart.iste.sopraws20.api.values;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.litesoftwares.coingecko.domain.Coins.MarketChart;

import de.unistuttgart.iste.sopraws20.api.statistic.Calculator;

/**
 * Class for Crypto currency model
 */
public class CryptoCurrency extends HistoricalValue {

	@NotNull
	private String logoUrl;
	@NotNull
	private MarketChart minutelyChart;
	@NotNull
	private MarketChart hourlyChart;
	@NotNull
	private MarketChart dailyChart;

	/**
	 * Constructor
	 *
	 * @param id
	 */
	public CryptoCurrency(String id) {
		super(id);
		logoUrl = "";
		minutelyChart = new MarketChart();
		hourlyChart = new MarketChart();
		dailyChart = new MarketChart();

	}

	/**
	 * returns price data in specified interval. today and 24h use minutely data.
	 * week and month use hourly data. last_month and year use daily data
	 * 
	 * @param timeframe today, 24h, week, month, last_month or year
	 * @return
	 */
	public List<List<String>> getPriceChartByTimeframe(String timeframe) {
		if (timeframe.equals("today")) {
			return Calculator.cutDataByIntervall("today", this.getMinutelyChart().getPrices());
		} else if (timeframe.equals("24h")) {
			return this.getMinutelyChart().getPrices();
		} else if (timeframe.equals("week")) {
			return Calculator.cutDataByIntervall("week", this.getHourlyChart().getPrices());
		} else if (timeframe.equals("month")) {
			return this.getHourlyChart().getPrices();
		} else if (timeframe.equals("last_month")) {
			return Calculator.cutDataByIntervall("last_month", this.getDailyChart().getPrices());
		} else {
			return this.getDailyChart().getPrices();
		}
	}

	/**
	 * returns marketcap data in specified interval. today and 24h use minutely
	 * data. week and month use hourly data. last_month and year use daily data
	 * 
	 * @param timeframe today, 24h, week, month, last_month or year
	 * @return
	 */
	public List<List<String>> getMarketCapChartByTimeframe(String timeframe) {
		if (timeframe.equals("today")) {
			return Calculator.cutDataByIntervall("today", this.getMinutelyChart().getMarketCaps());
		} else if (timeframe.equals("24h")) {
			return this.getMinutelyChart().getMarketCaps();
		} else if (timeframe.equals("week")) {
			return Calculator.cutDataByIntervall("week", this.getHourlyChart().getMarketCaps());
		} else if (timeframe.equals("month")) {
			return this.getHourlyChart().getMarketCaps();
		} else if (timeframe.equals("last_month")) {
			return Calculator.cutDataByIntervall("last_month", this.getDailyChart().getMarketCaps());
		} else {
			return this.getDailyChart().getMarketCaps();
		}
	}

	/**
	 * returns marketcap data in specified interval. today and 24h use minutely
	 * data. week and month use hourly data. last_month and year use daily data
	 * 
	 * @param timeframe today, 24h, week, month, last_month or year
	 * @return
	 */
	public List<List<String>> getVolumeChartByTimeframe(String timeframe) {
		if (timeframe.equals("today")) {
			return Calculator.cutDataByIntervall("today", this.getMinutelyChart().getTotalVolumes());
		} else if (timeframe.equals("24h")) {
			return this.getMinutelyChart().getTotalVolumes();
		} else if (timeframe.equals("week")) {
			return Calculator.cutDataByIntervall("week", this.getHourlyChart().getTotalVolumes());
		} else if (timeframe.equals("month")) {
			return this.getHourlyChart().getTotalVolumes();
		} else if (timeframe.equals("last_month")) {
			return Calculator.cutDataByIntervall("last_month", this.getDailyChart().getTotalVolumes());
		} else {
			return this.getDailyChart().getTotalVolumes();
		}
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
