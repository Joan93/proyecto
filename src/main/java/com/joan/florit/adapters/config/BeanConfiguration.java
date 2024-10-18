package com.joan.florit.adapters.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.joan.florit.adapters.output.persistence.PricePersistenceAdapter;
import com.joan.florit.adapters.output.persistence.mapper.PriceMapper;
import com.joan.florit.adapters.output.persistence.repository.PriceRepository;
import com.joan.florit.domain.service.PriceService;

@Configuration
public class BeanConfiguration {
    
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public PriceMapper priceMapper(){
        return new PriceMapper();
    }
    
    @Bean
    public PricePersistenceAdapter pricePersistenceAdapter(PriceRepository priceRepository, PriceMapper priceMapper) {
        return new PricePersistenceAdapter(priceRepository, priceMapper);
    }

    @Bean
    public PriceService priceService(PricePersistenceAdapter pricePersistenceAdapter) {
        return new PriceService(pricePersistenceAdapter);
    }

}
