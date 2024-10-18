package com.joan.florit.adapters.output.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.joan.florit.adapters.output.persistence.entity.PriceEntity;
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
    public List<Price> getPricesByParams(Integer brandId, Integer productId) {

        String dateTimeStr = "2020-10-18T15:30:45";
        LocalDateTime date2 = LocalDateTime.parse(dateTimeStr);

       var priceEntities = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId,productId,date2,date2);

       if(priceEntities.isEmpty()){
           return List.of();
       }

       return priceEntities.stream().map(entity -> this.priceMapper.toDomain(entity)).toList();
    }

}
