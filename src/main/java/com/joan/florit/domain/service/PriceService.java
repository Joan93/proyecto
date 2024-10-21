package com.joan.florit.domain.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Comparator;

import com.joan.florit.domain.exception.PriceNotFoundException;
import com.joan.florit.domain.model.Price;
import com.joan.florit.application.input.PriceUseCase;
import com.joan.florit.application.output.PriceOutputPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PriceService implements PriceUseCase {

    private final PriceOutputPort priceOutputPort;

    @Override
    public Price getPrice(String date, Integer productId, Integer brandId) {

        validateInputParameters(date, productId, brandId);

        var prices = priceOutputPort.getPricesByParams(getDateTime(date), brandId, productId);

        return prices.stream() //
                .sorted(Comparator.comparing(Price::getPriority).reversed()) //
                .findFirst() //
                .orElseThrow(() -> new PriceNotFoundException("Error price not found"));
    }

    private  void validateInputParameters(final String date, final Integer productId, final Integer brandId) {
        if(productId == null || brandId == null || date == null){
            throw new PriceNotFoundException("Error parameters productId, brandId, date are mandatory");
        }
    }

    private LocalDateTime getDateTime(final String date) {
        try {
            return LocalDateTime.parse(date);
        } catch (DateTimeParseException e) {
            throw new PriceNotFoundException("Error formatting date: pattern is yyyy-MM-dd'T'HH:mm:ss");
        }
    }

}
