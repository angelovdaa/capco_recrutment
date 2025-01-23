package org.capco.shopping_cart.infrastructure.database.product;

import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.entities.product.ProductType;
import org.capco.shopping_cart.domain.fixtures.ProductFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class ProductDatabaseAdapterTest {

    @InjectMocks
    ProductDatabaseAdapter productDatabaseAdapter;

    @Mock
    ProductJPARepository productJPARepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void saveProduct_shouldSaveByUsingJpaRepository() {

        // Given
        Product product = ProductFixture.aLaptop();
        ArgumentCaptor<ProductJPA> productJPAArgumentCaptor = ArgumentCaptor.forClass(ProductJPA.class);

        // When
        productDatabaseAdapter.save(product);

        // Then
        verify(productJPARepository).save(productJPAArgumentCaptor.capture());
        assertThat(productJPAArgumentCaptor.getValue()).isEqualToComparingOnlyGivenFields(product, "id", "productType", "name", "description");
    }

    @Test
    void find_shouldReturnProductByNameContainsIgnoreCase() {
        // Given
        List<ProductJPA> expected = List.of(testEntityManager.find(ProductJPA.class, "47a79602-86b9-4925-99a2-e180dbc1512c"), testEntityManager.find(ProductJPA.class, "47a79602-86b9-4925-99a2-e180dbc1512c"));

        // When
        doReturn(expected).when(productJPARepository).findByNameContainsIgnoreCase("ipho");

        // Then
        List<Product> actual = productDatabaseAdapter.getByNameContainsIgnoreCase("ipho");
        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual.get(0)).isEqualToComparingOnlyGivenFields(expected.get(0), "id", "productType", "name", "description");
        assertThat(actual.get(1)).isEqualToComparingOnlyGivenFields(expected.get(0), "id", "productType", "name", "description");
    }

    @Test
    void find_shouldReturnProductByproductType() {
        // Given
        List<ProductJPA> expected = List.of(testEntityManager.find(ProductJPA.class, "47a79602-86b9-4925-99a2-e180dbc1512c"),
                testEntityManager.find(ProductJPA.class, "08d0a16f-ab32-46a0-bf33-0c03f5da3f5c"));
        // When
        doReturn(expected).when(productJPARepository).findByProductType(ProductType.SMARTPHONE_PREMIUM);

        // Then
        List<Product> actual = productDatabaseAdapter.getByProductType(ProductType.SMARTPHONE_PREMIUM);
        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual.get(0)).isEqualToComparingOnlyGivenFields(expected.get(0), "id", "productType", "name", "description");
        assertThat(actual.get(1)).isEqualToComparingOnlyGivenFields(expected.get(1), "id", "productType", "name", "description");
    }

}
