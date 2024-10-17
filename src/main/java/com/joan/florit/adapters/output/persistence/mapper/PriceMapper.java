package com.joan.florit.adapters.output.persistence.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.joan.florit.adapters.output.persistence.entity.PriceEntity;
import com.joan.florit.domain.model.Price;

public class PriceMapper {

    @Autowired
    private ModelMapper mapper;

    public Price toDomain(PriceEntity entity){
        return mapper.map(entity, Price.class);
    }
    
    public PriceEntity toEntity(Price product){
        return mapper.map(product, PriceEntity.class);
    }

}
