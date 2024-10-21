package com.joan.florit.infraestructure.output.persistence;

import java.time.LocalDateTime;
import java.util.List;

import com.joan.florit.domain.exception.PriceNotFoundException;
import com.joan.florit.infraestructure.output.persistence.mapper.PriceMapper;
import com.joan.florit.infraestructure.output.persistence.repository.PriceRepository;
import com.joan.florit.domain.model.Price;
import com.joan.florit.application.output.PriceOutputPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PricePersistenceAdapter implements PriceOutputPort {

    private final PriceRepository priceRepository;

    private final PriceMapper priceMapper;

    @Override
    public List<Price> getPricesByParams(LocalDateTime date, Integer brandId, Integer productId) {

        var priceEntities = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, date, date);

        if (priceEntities.isEmpty()) {
           return List.of();
        }

        return priceEntities.stream()//
                .map(entity -> this.priceMapper.toDomain(entity))//
                .toList();
    }

}
