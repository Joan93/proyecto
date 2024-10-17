package com.joan.florit.adapters.input.rest.data.request;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequest {

    private LocalDateTime date;
    private Integer productId;
    private Integer brandId;

}

