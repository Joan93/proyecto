package com.joan.florit.adapters.input;

import java.time.LocalDateTime;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joan.florit.adapters.input.rest.data.request.PriceRequest;
import com.joan.florit.adapters.input.rest.data.response.PriceResponse;
import com.joan.florit.ports.input.PriceUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ProductRestAdapter {

    private final PriceUseCase priceUseCase;

    private final ModelMapper mapper;

    @GetMapping(value = "/products")
    public ResponseEntity<PriceResponse> getPrice(@RequestParam(value = "date") String date,
                                                  @RequestParam Integer productId,
                                                  @RequestParam Integer brandId){
        var price = priceUseCase.getPrice(new Date(),productId,brandId);
        return new ResponseEntity<>(mapper.map(price, PriceResponse.class), HttpStatus.OK);
    }
    
}
