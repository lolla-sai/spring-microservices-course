package com.courses.spring_boot_microservices.product;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.testcontainers.containers.MongoDBContainer;

import com.courses.spring_boot_microservices.product.ProductResponse.ProductResponse;

import lombok.extern.slf4j.Slf4j;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class ProductServiceApplicationTests {
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

	@LocalServerPort
	private Integer randomServerPort;
	@Autowired
	TestRestTemplate testRestTemplate;

	private String baseUrl;

	@BeforeEach
	void setUp() {
		baseUrl = "http://localhost:" + randomServerPort + "/api/product/";
		log.info("Base URL: " + baseUrl);
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
						{
						    "name": "iPhone 15",
						    "description": "Latest Apple iPhone model",
						    "price": 999.99
						}
				""";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

		ProductResponse productResponse = testRestTemplate.postForObject(baseUrl, entity, ProductResponse.class);
		log.info("Created Product: {}", productResponse);

		assertThat(productResponse.id(), notNullValue());
		assertThat(productResponse.name(), equalTo("iPhone 15"));
		assertThat(productResponse.price(), equalTo(BigDecimal.valueOf(999.99)));
		assertThat(productResponse.description(), equalTo("Latest Apple iPhone model"));
	}

	@Test
	void shouldGetAllProducts() {
		ProductResponse[] products = testRestTemplate.getForObject(baseUrl, ProductResponse[].class);

		log.info("Products List: " + Arrays.toString(products));

		// Validate the length of the array
		assertThat(Arrays.asList(products), hasSize(greaterThan(0))); // Change 1 to expected number of products

		// Validate individual product objects
		assertThat(products[0].name(), equalTo("iPhone 15"));
	}
}
