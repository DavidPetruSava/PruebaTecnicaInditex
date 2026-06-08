package com.inditex.priceservice.infrastructure.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    @Query(value = """
            SELECT * FROM PRICES
            WHERE product_id = :productId
              AND brand_id = :brandId
              AND :applicationDate >= start_date
              AND :applicationDate <= end_date
            ORDER BY priority DESC
            LIMIT 1
            """, nativeQuery = true)
    Optional<PriceEntity> findApplicablePrice(
            @Param("applicationDate") LocalDateTime applicationDate,
            @Param("productId") Long productId,
            @Param("brandId") Integer brandId
    );
}