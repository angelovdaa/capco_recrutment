package org.capco.shopping_cart.infrastructure.database.price;

import org.capco.shopping_cart.domain.entities.id.UUIDGenerator;
import org.capco.shopping_cart.domain.entities.product.ProductType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class PriceRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PriceJPARepository priceJPARepository;

    @Test
    void save_shouldReturnProductPriceSaved() {
        PriceJPA expected = new PriceJPA();
        expected.setId(UUIDGenerator.generate());
        expected.setProductType(ProductType.SMARTPHONE_PREMIUM);
        expected.setPrice(1000.0);
        expected.setCurrencyCode("USD");

        // When
        priceJPARepository.save(expected);

        // Then
        PriceJPA actual = testEntityManager.find(PriceJPA.class, expected.getId());
        assertThat(expected).isEqualToComparingFieldByField(actual);
    }

    @Test
    void find_shouldReturnPriceProductTypeAndCurrency() {

        // Given
        PriceJPA expected = testEntityManager.find(PriceJPA.class, "46a9b7f0-4ed0-4b6a-acce-8a81913eaa07");
        // When
        List<PriceJPA> actual = priceJPARepository.findByProductTypeAndCurrencyCode(ProductType.SMARTPHONE_PREMIUM, "EUR");
        // Then
        assertThat(actual.get(0)).isEqualToComparingFieldByField(expected);

    }

}
