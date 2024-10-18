package com.joan.florit.domain.service;

import static org.mockito.MockitoAnnotations.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import com.joan.florit.domain.exception.PriceNotFoundException;
import com.joan.florit.domain.model.Price;
import com.joan.florit.ports.output.PriceOutputPort;

import java.util.Collections;
import java.util.List;

public class PriceServiceTest {

    @Mock
    private PriceOutputPort priceOutputPort;

    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void testGetPrice_Success() {

        var date = "2020-06-14T16:00:00";
        var productId = 35455;
        var brandId = 1;

        var price1 = Price.builder()//
                .brandId(1)//
                .productId(35455)//
                .priority(0)//
                .build();

        var price2 = Price.builder()//
                .brandId(1)//
                .productId(35455)//
                .priority(1)//
                .build(); //

        when(priceOutputPort.getPricesByParams(date, brandId, productId)).thenReturn(List.of(price1, price2));

        var result = priceService.getPrice(date, productId, brandId);

        assertEquals(price2, result);
    }

    @Test
    public void testGetPrice_PriceNotFound() {

        var date = "2024-10-10";
        var productId = 1;
        var brandId = 1;

        when(priceOutputPort.getPricesByParams(date, brandId, productId)).thenReturn(Collections.emptyList());

        assertThrows(PriceNotFoundException.class, () -> priceService.getPrice(date, productId, brandId));
    }
}
