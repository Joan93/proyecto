package com.joan.florit.restassured;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.joan.florit.adapters.input.PriceRestAdapter;
import com.joan.florit.adapters.input.rest.data.response.PriceResponse;
import com.joan.florit.domain.model.Price;
import com.joan.florit.ports.input.PriceUseCase;

import jakarta.annotation.PostConstruct;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceRestAdapterTest {

    @LocalServerPort
    private int port;

    private String uri;

    @PostConstruct
    public void init() {
	uri = "http://localhost:" + port;
    }

    @InjectMocks
    private PriceRestAdapter priceRestAdapter;

    @MockBean
    private PriceUseCase priceUseCase;

    @MockBean
    private ModelMapper mapper;

    @Test
    public void testGetPrice() {

	when(priceUseCase.getPrice(any(), any(), any())).thenReturn(new Price());

	var mockResponse = PriceResponse.builder()
		.brandId(1)//
		.productId(35455)//
		.currency("EUR")//
		.priceList(3)//
		.startDate(LocalDateTime.parse("2020-06-15T00:00:00"))//
		.endDate(LocalDateTime.parse("2020-06-15T11:00:00"))//
		.price(new BigDecimal(30.50))//
		.build();

	when(mapper.map(any(), any())).thenReturn(mockResponse);

	given()
		.queryParam("date", "2020-06-15T10:00:00")
		.queryParam("productId", 35455)
		.queryParam("brandId", 1)
		.accept(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.get(uri + "/v1/prices")
		.then()
		.statusCode(HttpStatus.OK.value())
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.body("productId", org.hamcrest.Matchers.equalTo(35455))
		.body("brandId", org.hamcrest.Matchers.equalTo(1))
		.body("currency", org.hamcrest.Matchers.equalTo("EUR"))
	;
    }
}
