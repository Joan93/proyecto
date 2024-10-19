package com.joan.florit.adapters.output.persistence;

import java.time.LocalDateTime;
import java.util.List;

import com.joan.florit.adapters.output.persistence.mapper.PriceMapper;
import com.joan.florit.adapters.output.persistence.repository.PriceRepository;
import com.joan.florit.domain.model.Price;
import com.joan.florit.ports.output.PriceOutputPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PricePersistenceAdapter implements PriceOutputPort {

    private final PriceRepository priceRepository;

    private final PriceMapper priceMapper;

    @Override
    public List<Price> getPricesByParams(String date, Integer brandId, Integer productId) {

        var dateTime = LocalDateTime.parse(date);
        var priceEntities = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, dateTime, dateTime);

        if (priceEntities.isEmpty()) {
           return List.of();
        }

        return priceEntities.stream()//
                .map(entity -> this.priceMapper.toDomain(entity))//
                .toList();
    }

}
