package de.unistuttgart.iste.sopraws20.api;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.domain.Coins.CoinList;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

@SpringBootApplication

public class Application {

	public static void main(String[] args) {
		CoinGeckoApiClient client = new CoinGeckoApiClientImpl();
		// load list of coins
		List<CoinList> coinList = client.getCoinList();
		// private Marketclient.getCoinMarketChartById("bitcoin", "eur", 1);
		System.out.println(coinList);
		SpringApplication.run(Application.class, args);
	}

	// enable cross-origin resource sharing (CORS)
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// allow CORS requests for all resources and HTTP methods from the frontend
				// origin
				registry.addMapping("/**").allowedMethods("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE")
						.allowedOrigins("http://localhost:8000");
			}
		};
	}
}
