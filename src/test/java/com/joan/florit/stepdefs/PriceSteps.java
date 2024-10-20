package com.joan.florit.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

public class PriceSteps {

    @Autowired
    private TestRestTemplate restTemplate = new TestRestTemplate();

    private String productId;
    private String brandId;
    private String date;
    private ResponseEntity<String> response;

    @Given("I have the productId {int}, brandId {int}, and date {string}")
    public void i_have_the_productId_brandId_and_date(int productId, int brandId, String date) {
	this.productId = String.valueOf(productId);
	this.brandId = String.valueOf(brandId);
	this.date = date;
    }

    @When("I request the price from the price service")
    public void i_request_the_price_from_the_price_service() {
	response = restTemplate.getForEntity("http://localhost:8080/v1/prices?productId=" + productId + "&brandId=" + brandId + "&date=" + date, String.class);
    }

    @Then("I should receive a status code of {int}")
    public void i_should_receive_a_status_code_of(int statusCode) {
	assertEquals(statusCode, response.getStatusCodeValue());
    }

    @Then("the response should contain productId {int}, brandId {int}, and price {double}")
    public void the_response_should_contain_productId_brandId_and_price(int expectedProductId, int expectedBrandId, double expectedPrice) {
	var responseBody = response.getBody();
	assertTrue(responseBody.contains("\"productId\":" + expectedProductId));
	assertTrue(responseBody.contains("\"brandId\":" + expectedBrandId));
	assertTrue(responseBody.contains("\"price\":" + expectedPrice));
    }
}
