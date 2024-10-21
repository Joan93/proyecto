package com.joan.florit.application.input;

import java.time.LocalDateTime;
import java.util.Date;

import com.joan.florit.domain.model.Price;

public interface PriceUseCase {
   
    Price getPrice(String date, Integer productId, Integer brandId);
    
}
