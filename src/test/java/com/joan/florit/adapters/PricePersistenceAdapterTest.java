package com.joan.florit.adapters;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.joan.florit.adapters.output.persistence.PricePersistenceAdapter;
import com.joan.florit.adapters.output.persistence.entity.PriceEntity;
import com.joan.florit.adapters.output.persistence.mapper.PriceMapper;
import com.joan.florit.adapters.output.persistence.repository.PriceRepository;
import com.joan.florit.domain.model.Price;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PricePersistenceAdapterTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PricePersistenceAdapter pricePersistenceAdapter;

    @Test
    void testGetPricesByParams_withNoResults() {

        String date = "2024-10-18T10:00:00";
        LocalDateTime dateTime = LocalDateTime.parse(date);
        Integer brandId = 1;
        Integer productId = 1;

        when(priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                brandId, productId, dateTime, dateTime)).thenReturn(Collections.emptyList());

        List<Price> result = pricePersistenceAdapter.getPricesByParams(date, brandId, productId);

        assertTrue(result.isEmpty(), "The result should be empty");
    }

    @Test
    void testGetPricesByParams_withResults() {

        String date = "2024-10-18T10:00:00";
        LocalDateTime dateTime = LocalDateTime.parse(date);
        Integer brandId = 1;
        Integer productId = 1;

        PriceEntity priceEntity = new PriceEntity();
        List<PriceEntity> priceEntities = List.of(priceEntity);
        when(priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                brandId, productId, dateTime, dateTime)).thenReturn(priceEntities);

        Price price = new Price();
        when(priceMapper.toDomain(priceEntity)).thenReturn(price);

        List<Price> result = pricePersistenceAdapter.getPricesByParams(date, brandId, productId);

        assertFalse(result.isEmpty(), "The result should not be empty");
        assertEquals(1, result.size(), "The result should contain one element");
        assertEquals(price, result.get(0), "The mapped price should be returned");
    }
}
