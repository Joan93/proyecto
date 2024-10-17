package com.joan.florit.ports.output;

import java.util.List;
import java.util.Optional;

import com.joan.florit.domain.model.Price;

public interface PriceOutputPort {

    Optional<Price> getPriceById(Long id);

    List<Price> getPricesByParams();

}
