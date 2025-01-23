package org.capco.shopping_cart.infrastructure.database.price;

import org.capco.shopping_cart.domain.entities.product.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Currency;
import java.util.List;
import java.util.OptionalDouble;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class PriceDatabaseAdapterTest {
    @InjectMocks
    PriceDatabaseAdapter priceDatabaseAdapter;

    @Mock
    PriceJPARepository priceJPARepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void find_shouldReturnPriceByPriceTypeAndProductTypeAndCurrency() {
        // Given
        Currency EUR = Currency.getInstance("EUR");
        List<PriceJPA> expectedPrice = List.of(testEntityManager.find(PriceJPA.class, "46a9b7f0-4ed0-4b6a-acce-8a81913eaa07"));
        // When
        doReturn(expectedPrice).when(priceJPARepository).findByProductTypeAndCurrencyCode(ProductType.SMARTPHONE_PREMIUM, "EUR");

        // Then
        OptionalDouble actual = priceDatabaseAdapter.getForProductType(ProductType.SMARTPHONE_PREMIUM, EUR.getCurrencyCode());
        Assertions.assertTrue(actual.isPresent());
        assertThat(actual.getAsDouble()).isEqualToComparingOnlyGivenFields(expectedPrice.get(0).getPrice());
    }
}
