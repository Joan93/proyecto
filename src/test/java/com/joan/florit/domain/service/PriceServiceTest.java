package com.joan.florit.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        MockitoAnnotations.openMocks(this); // Initialize mocks before each test
    }

    @Test
    public void testGetPrice_Success() {

        String date = "2020-06-14T16:00:00";
        Integer productId = 35455;
        Integer brandId = 1;

        Price price1 = Price.builder()//
                .brandId(1)//
                .productId(35455)//
                .priority(0)//
                .build();

        Price price2 = Price.builder()//
                .brandId(1)//
                .productId(35455)//
                .priority(1)//
                .build(); //

        List<Price> prices = List.of(price1, price2);

        when(priceOutputPort.getPricesByParams(date, brandId, productId)).thenReturn(prices);

        Price result = priceService.getPrice(date, productId, brandId);

        assertEquals(price2, result);
    }

    @Test
    public void testGetPrice_PriceNotFound() {

        String date = "2024-10-10";
        Integer productId = 1;
        Integer brandId = 1;

        when(priceOutputPort.getPricesByParams(date, brandId, productId)).thenReturn(Collections.emptyList());

        assertThrows(PriceNotFoundException.class, () -> priceService.getPrice(date, productId, brandId));
    }
}
