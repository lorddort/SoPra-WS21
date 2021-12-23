package de.unistuttgart.iste.sopraws20.api.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import de.unistuttgart.iste.sopraws20.api.RESTController.CryptoCurrencyController;
import de.unistuttgart.iste.sopraws20.api.values.CryptoCurrency;

@SpringBootTest
@AutoConfigureMockMvc
class CryptoCurrencyControllerTest {

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

	@Test
	void postBitcoinTest() {
		controller.addCryptoCurrency("bitcoin");
		assertEquals(controller.getLoadedCryptoCurrencies().get(0).getName(), crypto.getName());
		assertTrue(controller.getCryptoCurrencyByName("bitcoin").getDescription().length() > 1);
		assertTrue(controller.getCryptoCurrencyByName("bitcoin").getLogoUrl().length() > 1);

	}

}
