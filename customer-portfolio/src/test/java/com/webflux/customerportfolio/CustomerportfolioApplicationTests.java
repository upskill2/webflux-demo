package com.webflux.customerportfolio;

import com.webflux.customerportfolio.domain.Ticker;
import com.webflux.customerportfolio.domain.TradeAction;
import com.webflux.customerportfolio.dto.StockTradeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
@AutoConfigureWebTestClient
class CustomerportfolioApplicationTests {

	@Autowired
	private WebTestClient webClient;

	@Test
	void contextLoads() {
	}

	@Test
	void testGetCustomerInfo() {
		webClient.get().uri("/customers/1")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.id").isEqualTo(1)
				.jsonPath("$.name").isEqualTo("Sam")
				.jsonPath("$.balance").isEqualTo(10000.0);
	}

	@Test
	void postTrade() {
		StockTradeRequest stockTradeRequest = new StockTradeRequest(Ticker.APPL, 3.0, 8, TradeAction.BUY);

		webClient.post().uri("/customers/1")
				.contentType (org.springframework.http.MediaType.APPLICATION_JSON)
				.bodyValue(stockTradeRequest)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.customerId").isEqualTo(1)
				.jsonPath("$.ticker").isEqualTo("APPL")
				.jsonPath("$.quantity").isEqualTo(8)
				.jsonPath ("$.totalPrice").isEqualTo(24.0);
	}

}
