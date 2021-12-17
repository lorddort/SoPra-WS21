package de.unistuttgart.iste.sopraws20.api.values;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.litesoftwares.coingecko.domain.Coins.MarketChart;

public class HistoricalValue {
	@NotNull
	@Size(min = 1)
	private String id;
	@NotNull
	@Size(min = 1)
	private String name;
	@NotNull
	private MarketChart standardMarketChart;
	@NotNull
	private String description;

	public HistoricalValue(String id) {
		this.id = id;
		this.name = "btc";
		this.description = "btc";
		this.standardMarketChart = new MarketChart();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getId() {
		return id;
	}

}
