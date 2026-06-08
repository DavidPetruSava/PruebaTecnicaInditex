package com.inditex.priceservice.domain.exception;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(Long productId, Integer brandId, String applicationDate) {
        super(String.format(
                "No applicable price found for productId=%d, brandId=%d, date=%s",
                productId, brandId, applicationDate
        ));
    }
}