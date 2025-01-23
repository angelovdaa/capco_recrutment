package org.capco.shopping_cart.domain.use_cases;

import org.assertj.core.util.Lists;
import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.fixtures.CustomerFixture;
import org.capco.shopping_cart.domain.fixtures.ProductFixture;
import org.capco.shopping_cart.domain.use_cases.price.DiscountPort;
import org.capco.shopping_cart.domain.use_cases.price.GetDiscounts;
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
public class GetDiscountsTest {

    @InjectMocks
    GetDiscounts getDiscounts;

    @Mock
    DiscountPort discountPort;


    @Test
    void testGetDiscounts_shouldReturnEmptyList() {
        // Given
        List<Product> productList = List.of(ProductFixture.random(), ProductFixture.random(), ProductFixture.random());
        List<Customer> customerList = List.of(CustomerFixture.random(), CustomerFixture.random());
        Random random = new Random();
        Product product = productList.get(random.nextInt(productList.size()));
        Customer customer = customerList.get(random.nextInt(customerList.size()));
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        // When
        doReturn(Lists.emptyList()).when(discountPort).getForCustomerProduct(customerArgumentCaptor.capture(), productArgumentCaptor.capture());

        // Then
        assertThat(getDiscounts.execute(customer, product)).isEmpty();
        verify(discountPort, times(1)).getForCustomerProduct(customerArgumentCaptor.capture(), productArgumentCaptor.capture());
        assertThat(product).isEqualTo(productArgumentCaptor.getValue());
        assertThat(customer).isEqualTo(customerArgumentCaptor.getValue());

    }

}
