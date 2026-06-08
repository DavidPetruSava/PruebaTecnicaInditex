package com.inditex.priceservice.infrastructure.in.rest.mapper;

import com.inditex.priceservice.domain.model.Price;
import com.inditex.priceservice.infrastructure.in.rest.PriceResponse;
import org.springframework.stereotype.Component;

@Component
public class PriceResponseMapper {

    public PriceResponse toResponse(Price price) {
        return new PriceResponse(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice(),
                price.getCurrency()
        );
    }
}