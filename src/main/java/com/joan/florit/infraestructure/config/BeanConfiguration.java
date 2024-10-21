package com.joan.florit.infraestructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.joan.florit.infraestructure.output.persistence.PricePersistenceAdapter;
import com.joan.florit.infraestructure.output.persistence.mapper.PriceMapper;
import com.joan.florit.infraestructure.output.persistence.repository.PriceRepository;
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
