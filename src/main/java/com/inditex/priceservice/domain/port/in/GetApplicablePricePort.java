package com.inditex.priceservice.domain.port.in;

import com.inditex.priceservice.domain.model.Price;

import java.time.LocalDateTime;

public interface GetApplicablePricePort {

    Price getApplicablePrice(LocalDateTime applicationDate, Long productId, Integer brandId);
}