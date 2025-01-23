package org.capco.shopping_cart.domain.use_cases;

import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.customer.LegalCustomer;
import org.capco.shopping_cart.domain.entities.product.Money;
import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.entities.product.ProductType;
import org.capco.shopping_cart.domain.fixtures.CustomerFixture;
import org.capco.shopping_cart.domain.fixtures.ProductFixture;
import org.capco.shopping_cart.domain.use_cases.customer.AnnualRevenuePort;
import org.capco.shopping_cart.infrastructure.database.price.CustomerInMemoryDiscountAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NaturalLegalCustomerDiscountTest {

    @Mock
    AnnualRevenuePort annualRevenuePort;

    @InjectMocks
    CustomerInMemoryDiscountAdapter getDiscount;

    private static final double TEN_MILLION = 10000000;

    private static final
    List<Product> productList = List.of(ProductFixture.random(), ProductFixture.random(), ProductFixture.random());

    @Test
    void getDiscountNaturalCustomer_ShouldReturn_0() {

        // Given
        Random random = new Random();
        Product product = productList.get(random.nextInt(productList.size()));
        Customer naturalCustomer = CustomerFixture.aNaturalCustomer();
        ArgumentCaptor<LegalCustomer> argumentCaptor = ArgumentCaptor.forClass(LegalCustomer.class);

        // Then
        assertThat(getDiscount.getForCustomerProduct(naturalCustomer, product).get(0)).isEqualTo(CustomerInMemoryDiscountAdapter.ZERO_DISCOUNT);
        verify(annualRevenuePort, times(0)).getForCustomer(argumentCaptor.capture());
    }

    @Test
    void getDiscountLegalCustomer_shouldReturnDiscount() {

        // Given
        Random random = new Random();
        Product product = productList.get(random.nextInt(productList.size()));
        Customer legalCustomer = CustomerFixture.aLegalCustomer();
        ArgumentCaptor<LegalCustomer> legalCustomerArgumentCaptor = ArgumentCaptor.forClass(LegalCustomer.class);

        // When
        Money revenue = Money.euros(Math.round(Math.random() * 2 * TEN_MILLION));
        doReturn(revenue).when(annualRevenuePort).getForCustomer((LegalCustomer) legalCustomer);
        boolean revenue_less_than10_million = revenue.getAmount().doubleValue() <= TEN_MILLION;
        List<Double> expectedDiscount = getDiscount.getForCustomerProduct(legalCustomer, product);
        ProductType productType = product.getProductType();

        // Then
        assertThat(expectedDiscount.size()).isEqualTo(1);
        if (revenue_less_than10_million) {
            assertThat(expectedDiscount.get(0)).isEqualTo(ProductType.LAPTOP.equals(productType) ?
                    CustomerInMemoryDiscountAdapter.DISCOUNT_LAPTOP_LESS_EQ_10M :
                    ProductType.SMARTPHONE_PREMIUM.equals(productType) ? CustomerInMemoryDiscountAdapter.DISCOUNT_SMARTPHONE_PREMIUM_LESS_EQ_10M :
                            CustomerInMemoryDiscountAdapter.DISCOUNT_SMARTPHONE_STANDARD_LESS_EQ_10M);
        } else {
            assertThat(expectedDiscount.get(0)).isEqualTo(ProductType.LAPTOP.equals(productType) ?
                    CustomerInMemoryDiscountAdapter.DISCOUNT_LAPTOP_MORE_THAN_10M :
                    ProductType.SMARTPHONE_PREMIUM.equals(productType) ? CustomerInMemoryDiscountAdapter.DISCOUNT_SMARTPHONE_PREMIUM_MORE_THAN_10M :
                            CustomerInMemoryDiscountAdapter.DISCOUNT_SMARTPHONE_STANDARD_MORE_THAN_10M);
        }

        verify(annualRevenuePort, times(1)).getForCustomer(legalCustomerArgumentCaptor.capture());

    }

}
