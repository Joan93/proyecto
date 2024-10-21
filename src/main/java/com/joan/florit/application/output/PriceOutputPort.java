package com.joan.florit.application.output;

import java.time.LocalDateTime;
import java.util.List;

import com.joan.florit.domain.model.Price;

public interface PriceOutputPort {

    List<Price> getPricesByParams(LocalDateTime date, Integer brandId, Integer productId);

}
