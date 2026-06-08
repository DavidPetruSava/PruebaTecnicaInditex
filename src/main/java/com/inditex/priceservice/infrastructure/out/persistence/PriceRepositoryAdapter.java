package com.inditex.priceservice.infrastructure.out.persistence;

import com.inditex.priceservice.domain.model.Price;
import com.inditex.priceservice.domain.port.out.PriceRepositoryPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceJpaRepository jpaRepository;

    public PriceRepositoryAdapter(PriceJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Price> findApplicablePrice(LocalDateTime applicationDate, Long productId, Integer brandId) {
        return jpaRepository
                .findApplicablePrice(applicationDate, productId, brandId)
                .map(this::toDomain);
    }

    private Price toDomain(PriceEntity entity) {
        return Price.builder()
                .productId(entity.getProductId())
                .brandId(entity.getBrandId())
                .priceList(entity.getPriceList())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .price(entity.getPrice())
                .currency(entity.getCurrency())
                .build();
    }
}