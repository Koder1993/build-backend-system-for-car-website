package com.udacity.pricing;

import com.udacity.pricing.entity.Price;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PricingServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getPriceForVehicleId() {
		long vehicleId = 2L;
		ResponseEntity<Price> response =
				testRestTemplate.getForEntity("http://localhost:" + port + "/prices/" + vehicleId, Price.class);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
		Price price = response.getBody();
		Assertions.assertNotNull(price);
		Assertions.assertEquals(price.getCurrency(), "INR");
	}
}
