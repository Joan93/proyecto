package com.joan.florit.infraestructure.input;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joan.florit.infraestructure.input.rest.data.response.PriceResponse;
import com.joan.florit.application.input.PriceUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
@Tag(name = "Price Rest Adapter", description = "Contains the prices operations")
public class PriceRestAdapter {

    private final PriceUseCase priceUseCase;
    private final ModelMapper mapper;

    @GetMapping(value = "/prices", produces = "application/json")
    @Operation(summary = "Obtains the price of a product by: productId, brandId, and date", description = "Returns information of product and the price")
    public ResponseEntity<PriceResponse> getPrice(@RequestParam(name = "date") String date, //
                                                  @RequestParam(name = "productId", required = false) Integer productId, //
                                                  @RequestParam(name = "brandId", required = false) Integer brandId) {
        var price = priceUseCase.getPrice(date, productId, brandId);
        return new ResponseEntity<>(mapper.map(price, PriceResponse.class), HttpStatus.OK);
    }
}