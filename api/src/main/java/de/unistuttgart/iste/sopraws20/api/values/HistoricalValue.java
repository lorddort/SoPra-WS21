package de.unistuttgart.iste.sopraws20.api.values;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.litesoftwares.coingecko.domain.Coins.MarketChart;

public class HistoricalValue {
	@NotNull
	@Size(min = 1)
	private String name;
	@NotNull
	private MarketChart standardMarketChart;
	@NotNull
	private String description;

	public HistoricalValue(String name, String description) {
		this.name = name;
		this.description = description;
		this.standardMarketChart = new MarketChart();
	}

	public MarketChart getStandardPriceChart() {
		return standardMarketChart;
	}

	public void setStandardPriceChart(MarketChart standardPriceChart) {
		this.standardMarketChart = standardPriceChart;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

}
