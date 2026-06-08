package com.inditex.priceservice.application.usecase;

import com.inditex.priceservice.domain.exception.PriceNotFoundException;
import com.inditex.priceservice.domain.model.Price;
import com.inditex.priceservice.domain.port.out.PriceRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetApplicablePriceUseCase")
class GetApplicablePriceUseCaseTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    private GetApplicablePriceUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetApplicablePriceUseCase(priceRepositoryPort);
    }

    @Test
    @DisplayName("should return price when found")
    void shouldReturnPriceWhenFound() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Price expected = buildPrice(1, 35.50);
        when(priceRepositoryPort.findApplicablePrice(date, 35455L, 1)).thenReturn(Optional.of(expected));

        Price result = useCase.getApplicablePrice(date, 35455L, 1);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("should throw PriceNotFoundException when no price found")
    void shouldThrowWhenNotFound() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        when(priceRepositoryPort.findApplicablePrice(date, 35455L, 1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.getApplicablePrice(date, 35455L, 1))
                .isInstanceOf(PriceNotFoundException.class)
                .hasMessageContaining("productId=35455")
                .hasMessageContaining("brandId=1");
    }

    private Price buildPrice(int priceList, double priceValue) {
        return Price.builder()
                .productId(35455L)
                .brandId(1)
                .priceList(priceList)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .price(BigDecimal.valueOf(priceValue))
                .currency("EUR")
                .build();
    }
}