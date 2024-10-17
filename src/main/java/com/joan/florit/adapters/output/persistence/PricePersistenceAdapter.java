package com.joan.florit.adapters.output.persistence;

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
    public Optional<Price> getPriceById(Long id) {
        Optional<PriceEntity> priceEntity = priceRepository.findById(id);

        if(priceEntity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(priceMapper.toDomain(priceEntity.get()));
    }

    @Override
    public List<Price> getPricesByParams() {
       var priceEntities = priceRepository.findAll();

       if(priceEntities.isEmpty()){
           return List.of();
       }

       return priceEntities.stream().map(entity -> this.priceMapper.toDomain(entity)).toList();
    }

}
