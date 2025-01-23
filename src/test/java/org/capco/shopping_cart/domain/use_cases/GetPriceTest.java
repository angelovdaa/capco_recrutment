package org.capco.shopping_cart.domain.use_cases;

import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.entities.product.ProductType;
import org.capco.shopping_cart.domain.fixtures.CustomerFixture;
import org.capco.shopping_cart.domain.fixtures.ProductFixture;
import org.capco.shopping_cart.domain.use_cases.price.GetDiscounts;
import org.capco.shopping_cart.domain.use_cases.price.GetPrice;
import org.capco.shopping_cart.domain.use_cases.price.PricePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.OptionalDouble;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPriceTest {

    private static final String EURO = "EUR";

    @InjectMocks
    GetPrice getPrice;

    @Mock
    PricePort pricePort;

    @Mock
    GetDiscounts getDiscounts;

    @Test
    void getPrice_shouldGetDiscountedPrice() {

        // Given
        OptionalDouble basicPrice = OptionalDouble.of(100);
        Customer customer = CustomerFixture.aLegalCustomer();
        Product product = ProductFixture.aLaptop();

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        ArgumentCaptor<ProductType> productTypeArgumentCaptor = ArgumentCaptor.forClass(ProductType.class);
        ArgumentCaptor<String> currencyArgumentCapture = ArgumentCaptor.forClass(String.class);

        // When
        when(pricePort.getForProductType(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(basicPrice);
        doReturn(List.of(0.2)).when(getDiscounts).execute(ArgumentMatchers.any(), ArgumentMatchers.any());
        assertThat(getPrice.execute(customer, product, EURO)).isEqualTo(OptionalDouble.of(80));

        // Then
        verify(pricePort, times(1)).getForProductType(productTypeArgumentCaptor.capture(), currencyArgumentCapture.capture());
        assertThat(product.getProductType()).isEqualTo(productTypeArgumentCaptor.getValue());
        assertThat(EURO).isEqualTo(currencyArgumentCapture.getValue());
        verify(getDiscounts, times(1)).execute(customerArgumentCaptor.capture(), productArgumentCaptor.capture());
        assertThat(product).isEqualToComparingFieldByField(productArgumentCaptor.getValue());


    }


}
