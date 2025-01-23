package org.capco.shopping_cart.domain.use_cases;

import org.capco.shopping_cart.domain.entities.cart.CartLineItem;
import org.capco.shopping_cart.domain.entities.cart.ShoppingCart;
import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.product.Money;
import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.fixtures.CustomerFixture;
import org.capco.shopping_cart.domain.fixtures.ProductFixture;
import org.capco.shopping_cart.domain.use_cases.price.GetPrice;
import org.capco.shopping_cart.domain.use_cases.shopping_cart.ComputeShoppingCart;
import org.capco.shopping_cart.domain.use_cases.shopping_cart.ShoppingCartException;
import org.capco.shopping_cart.domain.use_cases.shopping_cart.ShoppingCartPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Currency;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComputeShoppingCartTest {

    @InjectMocks
    ComputeShoppingCart computeShoppingCart;

    @Mock
    ShoppingCartPort shoppingCartPort;

    @Mock
    GetPrice getPrice;
    private static final
    List<Integer> quantities = List.of(3, 4, 5);

    private static final Currency EURO = Currency.getInstance("EUR");

    @Test
    void computeShoppingCart_shouldComputeShoppingCartForCustomer() {
        // Given
        Random random = new Random();
        Customer customer = CustomerFixture.random();
        ShoppingCart randomShoppingCart = new ShoppingCart(customer);
        // 3 random products with random quantities
        Product product1 = ProductFixture.random();
        Product product2 = ProductFixture.random();
        Product product3 = ProductFixture.random();

        int quantity1 = quantities.get(random.nextInt(quantities.size()));
        int quantity2 = quantities.get(random.nextInt(quantities.size()));
        int quantity3 = quantities.get(random.nextInt(quantities.size()));

        randomShoppingCart.addProduct(product1, quantity1);
        randomShoppingCart.addProduct(product2, quantity2);
        randomShoppingCart.addProduct(product3, quantity3);

        OptionalDouble price = OptionalDouble.of(100);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        ArgumentCaptor<String> currencyArgumentCapture = ArgumentCaptor.forClass(String.class);

        // When
        doReturn(randomShoppingCart).when(shoppingCartPort).getForCustomer(customer);
        when(getPrice.execute(ArgumentMatchers.<Customer>any(), ArgumentMatchers.<Product>any(), ArgumentMatchers.<String>any())).thenReturn(price);

        // Then
        Money totalAmount = computeShoppingCart.execute(customer, EURO);
        assertThat(totalAmount).isEqualTo(
                Money.euros(randomShoppingCart.getLineItems().stream().mapToInt(CartLineItem::getQuantity).sum() * price.getAsDouble()));

        verify(getPrice, times(randomShoppingCart.getLineItems().size())).execute(customerArgumentCaptor.capture(), productArgumentCaptor.capture(), currencyArgumentCapture.capture());
        assertThat(customer).isEqualToComparingFieldByField(customerArgumentCaptor.getValue());
    }

    @Test
    void computeShoppingCart_NoShoppingCartForCustomer_shouldThrowShoppingCartException() {

        // Given
        int quantity = 1;
        Customer customer = CustomerFixture.aNaturalCustomer();
        ShoppingCart randomShoppingCart = new ShoppingCart(customer);
        randomShoppingCart.addProduct(ProductFixture.random(), quantity);
        randomShoppingCart.addProduct(ProductFixture.random(), quantity);
        randomShoppingCart.addProduct(ProductFixture.random(), quantity);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        // When
        ShoppingCartException shoppingCartException = new ShoppingCartException("Mocked shopping cart exception", null);
        doThrow(shoppingCartException).when(shoppingCartPort).getForCustomer(customer);

        // Then
        assertThrows(ShoppingCartException.class, () -> computeShoppingCart.execute(customer, EURO));
        verify(shoppingCartPort, times(1)).getForCustomer(customerArgumentCaptor.capture());
        assertThat(customer).isEqualToComparingFieldByField(customerArgumentCaptor.getValue());
    }

    @Test
    void computeShoppingCart_NoPriceForProduct_shouldThrowShoppingCartException() {
        // Given
        int quantity = 1;
        Customer customer = CustomerFixture.aNaturalCustomer();
        ShoppingCart randomShoppingCart = new ShoppingCart(customer);
        List<Product> products = List.of(ProductFixture.random(), ProductFixture.random(), ProductFixture.random());
        randomShoppingCart.addProduct(products.get(0), quantity);
        randomShoppingCart.addProduct(products.get(1), quantity);
        randomShoppingCart.addProduct(products.get(2), quantity);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        ArgumentCaptor<String> currencyArgumentCapture = ArgumentCaptor.forClass(String.class);

        // When
        doReturn(randomShoppingCart).when(shoppingCartPort).getForCustomer(customer);
        doReturn(OptionalDouble.empty()).when(getPrice).execute(ArgumentMatchers.<Customer>any(), ArgumentMatchers.<Product>any(), ArgumentMatchers.<String>any());

        // Then
        assertThrows(ShoppingCartException.class, () -> computeShoppingCart.execute(customer, EURO));
        verify(shoppingCartPort, times(1)).getForCustomer(customerArgumentCaptor.capture());
        assertThat(customer).isEqualToComparingFieldByField(customerArgumentCaptor.getValue());
        verify(getPrice, times(1)).execute(customerArgumentCaptor.capture(), productArgumentCaptor.capture(), currencyArgumentCapture.capture());
        assertThat(products).containsAll(productArgumentCaptor.getAllValues());

    }


}
