package com.joan.florit.domain.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.joan.florit.adapters.input.rest.data.request.PriceRequest;
import com.joan.florit.domain.exception.PriceNotFoundException;
import com.joan.florit.domain.model.Price;
import com.joan.florit.ports.input.PriceUseCase;
import com.joan.florit.ports.output.PriceOutputPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PriceService implements PriceUseCase {

    private final PriceOutputPort priceOutputPort;

    @Override
    public Price getPrice(Date date, Integer productId, Integer brandId) {

        var prices = priceOutputPort.getPricesByParams();

        var date2 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

        return prices.stream()//
                .filter(p -> p.getBrandId().equals(brandId))//
                .filter(p-> p.getProductId().equals(productId))//
            //    .filter(p-> date2.isAfter(p.getStartDate()) && date2.isBefore(p.getEndDate()))//
                .findFirst().orElseThrow(() -> new PriceNotFoundException("Price not found"));

    }

}
