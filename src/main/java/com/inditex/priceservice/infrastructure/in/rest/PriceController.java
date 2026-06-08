package com.inditex.priceservice.infrastructure.in.rest;

import com.inditex.priceservice.domain.model.Price;
import com.inditex.priceservice.domain.port.in.GetApplicablePricePort;
import com.inditex.priceservice.infrastructure.in.rest.mapper.PriceResponseMapper;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final GetApplicablePricePort getApplicablePricePort;
    private final PriceResponseMapper mapper;

    public PriceController(GetApplicablePricePort getApplicablePricePort, PriceResponseMapper mapper) {
        this.getApplicablePricePort = getApplicablePricePort;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<PriceResponse> getApplicablePrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam Long productId,
            @RequestParam Integer brandId
    ) {
        Price price = getApplicablePricePort.getApplicablePrice(applicationDate, productId, brandId);
        return ResponseEntity.ok(mapper.toResponse(price));
    }
}