package org.capco.shopping_cart.infrastructure.database.product;

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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class ProductJPARepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    ProductJPARepository productJPARepository;

    @Test
    void save_shouldReturnProductSaved() {
        ProductJPA expected = new ProductJPA();
        expected.setId(UUIDGenerator.generate());
        expected.setProductType(ProductType.SMARTPHONE_PREMIUM);
        expected.setName("Iphone 26");
        expected.setDescription("The best phone ever");

        // When
        productJPARepository.save(expected);

        // Then
        ProductJPA actual = testEntityManager.find(ProductJPA.class, expected.getId());
        assertThat(expected).isEqualToComparingFieldByField(actual);
    }

    @Test
    void find_shouldReturnProductByProducType() {
        // Given
        String entityID1 = "47a79602-86b9-4925-99a2-e180dbc1512c";
        String entityID2 = "08d0a16f-ab32-46a0-bf33-0c03f5da3f5c";
        // When
        List<ProductJPA> actual = productJPARepository.findByProductType(ProductType.SMARTPHONE_PREMIUM);
        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual.get(0).getId()).isEqualTo(entityID1);
        assertThat(actual.get(1).getId()).isEqualTo(entityID2);
    }

    @Test
    void find_shouldFindProductByNameContainsIgnoreCase() {
        // Given
        String entityID1 = "47a79602-86b9-4925-99a2-e180dbc1512c";
        String entityID2 = "08d0a16f-ab32-46a0-bf33-0c03f5da3f5c";

        // When
        List<ProductJPA> actual = productJPARepository.findByNameContainsIgnoreCase("iphone");
        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual.get(0).getId()).isEqualTo(entityID1);
        assertThat(actual.get(1).getId()).isEqualTo(entityID2);

    }
}
