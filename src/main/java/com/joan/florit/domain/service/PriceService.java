package com.joan.florit.domain.service;

import java.util.Comparator;
import java.util.Date;

import com.joan.florit.domain.exception.PriceNotFoundException;
import com.joan.florit.domain.model.Price;
import com.joan.florit.ports.input.PriceUseCase;
import com.joan.florit.ports.output.PriceOutputPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PriceService implements PriceUseCase {

    private final PriceOutputPort priceOutputPort;

    @Override
    public Price getPrice(String date, Integer productId, Integer brandId) {

        var prices = priceOutputPort.getPricesByParams(brandId, productId);

        return prices.stream() //
                .sorted(Comparator.comparing(Price::getPriority).reversed())//
                .findFirst() //
                .orElseThrow(() -> new PriceNotFoundException("Price not found"));
    }

}
