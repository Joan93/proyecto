package com.joan.florit.adapters.input;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joan.florit.adapters.input.rest.data.response.PriceResponse;
import com.joan.florit.ports.input.PriceUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Sample API", description = "Sample operations for demonstration")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class PriceRestAdapter {

    private final PriceUseCase priceUseCase;

    private final ModelMapper mapper;

    @GetMapping(value = "/prices")
    @Operation(summary = "Say Hello", description = "Returns a greeting message")
    public ResponseEntity<PriceResponse> getPrice(@RequestParam String date,
                                                  @RequestParam Integer productId,
                                                  @RequestParam Integer brandId){
        var price = priceUseCase.getPrice(date, productId, brandId);
        return new ResponseEntity<>(mapper.map(price, PriceResponse.class), HttpStatus.OK);
    }
    
}
