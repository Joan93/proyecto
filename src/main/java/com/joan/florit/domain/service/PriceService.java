package com.joan.florit.domain.service;

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

        var prices = priceOutputPort.getPricesByParams(date, brandId, productId);

        return prices.stream() //
                .sorted(Comparator.comparing(Price::getPriority).reversed()) //
                .findFirst() //
                .orElseThrow(() -> new PriceNotFoundException("Error price not found"));
    }

}
