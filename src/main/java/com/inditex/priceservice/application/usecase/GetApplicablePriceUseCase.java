package com.inditex.priceservice.application.usecase;

import com.inditex.priceservice.domain.exception.PriceNotFoundException;
import com.inditex.priceservice.domain.model.Price;
import com.inditex.priceservice.domain.port.in.GetApplicablePricePort;
import com.inditex.priceservice.domain.port.out.PriceRepositoryPort;

import java.time.LocalDateTime;

public class GetApplicablePriceUseCase implements GetApplicablePricePort {

    private final PriceRepositoryPort priceRepositoryPort;

    public GetApplicablePriceUseCase(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    @Override
    public Price getApplicablePrice(LocalDateTime applicationDate, Long productId, Integer brandId) {
        return priceRepositoryPort
                .findApplicablePrice(applicationDate, productId, brandId)
                .orElseThrow(() -> new PriceNotFoundException(
                        productId, brandId, applicationDate.toString()
                ));
    }
}