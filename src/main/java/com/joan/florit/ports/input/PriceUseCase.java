package com.joan.florit.ports.input;

import java.time.LocalDateTime;
import java.util.Date;

import com.joan.florit.domain.model.Price;

public interface PriceUseCase {
   
    Price getPrice(Date date, Integer productId, Integer brandId);
    
}
