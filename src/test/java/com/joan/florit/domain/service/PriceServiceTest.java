package com.joan.florit.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.joan.florit.domain.exception.PriceNotFoundException;
import com.joan.florit.domain.model.Price;
import com.joan.florit.application.output.PriceOutputPort;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    @Mock
    private PriceOutputPort priceOutputPort;

    @InjectMocks
    private PriceService priceService;

    @Test
    public void getPriceSuccess() {

        var date = "2020-06-14T16:00:00";
        var productId = 35455;
        var brandId = 1;

        // first price with priority 0
        var price1 = Price.builder()//
                .brandId(1)//
                .productId(35455)//
                .priority(0)//
                .build();

        // second price with priority 1
        var price2 = Price.builder()//
                .brandId(1)//
                .productId(35455)//
                .priority(1)//
                .build(); //

        when(priceOutputPort.getPricesByParams(date, brandId, productId)).thenReturn(List.of(price1, price2));

        var result = priceService.getPrice(date, productId, brandId);

        // the result contains the second price
        assertEquals(price2, result);
    }

    @Test
    public void getPriceNotFound() {

        var date = "2024-10-10";
        var productId = 1;
        var brandId = 1;

        when(priceOutputPort.getPricesByParams(date, brandId, productId)).thenReturn(Collections.emptyList());

        assertThrows(PriceNotFoundException.class, () -> priceService.getPrice(date, productId, brandId));
    }
}
