package com.joan.florit.ports.output;

import java.util.List;

import com.joan.florit.domain.model.Price;

public interface PriceOutputPort {

    List<Price> getPricesByParams(Integer brandId, Integer productId);

}
