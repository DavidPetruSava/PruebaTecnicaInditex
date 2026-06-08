package com.inditex.priceservice.domain.port.out;

import com.inditex.priceservice.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {

    Optional<Price> findApplicablePrice(LocalDateTime applicationDate, Long productId, Integer brandId);
}