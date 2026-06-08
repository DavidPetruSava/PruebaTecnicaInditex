package com.inditex.priceservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inditex.priceservice.application.usecase.GetApplicablePriceUseCase;
import com.inditex.priceservice.domain.port.in.GetApplicablePricePort;
import com.inditex.priceservice.domain.port.out.PriceRepositoryPort;

@Configuration
public class UseCaseConfig {
    
    @Bean
    public GetApplicablePricePort getApplicablePricePort(PriceRepositoryPort repo) {
        return new GetApplicablePriceUseCase(repo);
  
    }
}
