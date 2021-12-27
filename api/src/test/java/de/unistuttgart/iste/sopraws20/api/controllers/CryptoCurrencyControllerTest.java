package de.unistuttgart.iste.sopraws20.api.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import de.unistuttgart.iste.sopraws20.api.services.CryptoCurrencyService;
import de.unistuttgart.iste.sopraws20.api.values.CryptoIdName;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import de.unistuttgart.iste.sopraws20.api.RESTController.CryptoCurrencyController;
import de.unistuttgart.iste.sopraws20.api.values.CryptoCurrency;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@WebMvcTest(CryptoCurrencyController.class)
class CryptoCurrencyControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CryptoCurrencyService cryptoCurrencyService;

	@Test
	public void getCryptoCurrencyNamesTest() throws Exception{
		when(cryptoCurrencyService.getCryptoCurrencyNames()).thenReturn(List.of(new CryptoIdName("bitcoin", "Bitcoin")));

		this.mockMvc.perform(MockMvcRequestBuilders.get("/cryptos/list"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("bitcoin"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Bitcoin"));
	}

	@Test
	public void getCryptoCurrencyNamesAmountTest() throws Exception{
		int amount = 10;
		when(cryptoCurrencyService.getCryptoCurrencyNames(amount))
			.thenReturn(List.of(new CryptoIdName("bitcoin", "Bitcoin"),
				new CryptoIdName("bitcoin", "Bitcoin"),
				new CryptoIdName("bitcoin", "Bitcoin"),
				new CryptoIdName("bitcoin", "Bitcoin"),
				new CryptoIdName("bitcoin", "Bitcoin"),
				new CryptoIdName("bitcoin", "Bitcoin"),
				new CryptoIdName("bitcoin", "Bitcoin"),
				new CryptoIdName("bitcoin", "Bitcoin"),
				new CryptoIdName("bitcoin", "Bitcoin"),
				new CryptoIdName("bitcoin", "Bitcoin")));

		this.mockMvc.perform(MockMvcRequestBuilders.get("/cryptos/list/10"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(amount));
	}

	@Disabled
	@Test
	public void getCryptoCurrencyByName() throws Exception{

	}

	@Disabled
	@Test
	public void getLoadedCryptoCurrencies() throws  Exception{

	}

	@Disabled
	@Test
	public void setLogoUrl() throws Exception{

	}

	@Test
	public void addCryptoCurrency() throws Exception{
		String id = "bitcoin";
		when(cryptoCurrencyService.addCryptoCurrency(id)).thenReturn(new CryptoCurrency(id));

		this.mockMvc.perform(MockMvcRequestBuilders.post("/cryptos/" + id)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"id\": \"bitcoin\"}"))
			.andExpect(status().isCreated());
	}

	@Disabled
	@Test
	public void editLogoUrl() throws Exception{

	}

	/*
	@Autowired
	private MockMvc mockMvc;
	static CryptoCurrencyController controller;
	static CryptoCurrency crypto;

	// bad test, long loading time and cant connect to server
	// @Test
	void testCryptoPost() {
		try {
			this.mockMvc.perform(post("/crypto/bitcoin")).andExpect(status().isCreated());
		} catch (Exception e) {
			fail("post request to make btc failed");
		}
	}

	// @Test
	void testCryptoGetIds() {
		try {
			this.mockMvc.perform(get("/crypto/list/1")).andExpect(status().isOk());
		} catch (Exception e) {
			fail("get request for id and name failed");
		}
	}

	@BeforeAll
	public static void init() {
		controller = new CryptoCurrencyController();
		//controller.init();
		crypto = new CryptoCurrency("bitcoin");
		crypto.setName("Bitcoin");

	}

	\\new Tests maybe since the backend structure is changed?
	\\if this test run CryptoCurrencyService is null and if thats define Instances of the Service are null

	//@Test
	void postBitcoinTest() {
		controller.addCryptoCurrency("bitcoin");
		assertEquals(controller.getLoadedCryptoCurrencies().get(0).getName(), crypto.getName());
		assertTrue(controller.getCryptoCurrencyByName("bitcoin").getDescription().length() > 1);
		assertTrue(controller.getCryptoCurrencyByName("bitcoin").getLogoUrl().length() > 1);

	}*/

}
