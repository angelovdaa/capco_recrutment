package org.capco.shopping_cart.domain.entities;

import org.capco.shopping_cart.domain.entities.cart.ShoppingCart;
import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.fixtures.CustomerFixture;
import org.capco.shopping_cart.domain.fixtures.ProductFixture;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartTest {

    @Test
    void shoppingCartTest_addNewProduct_shouldAddProductAndQuantityToCart() {
        // Given
        ShoppingCart shoppingCart = new ShoppingCart(CustomerFixture.aLegalCustomer());
        Product product = ProductFixture.aSmartphonePremium();
        int expectedQuantity = 1;
        // When
        shoppingCart.addProduct(product, expectedQuantity);
        // Then
        int actualItemsForProduct = (int) shoppingCart.getLineItems().stream()
                .filter(cartLineItem -> cartLineItem.getProduct().equals(product)).count();
        int actualQuantityForProduct = shoppingCart.getLineItems().stream()
                .filter(cartLineItem -> cartLineItem.getProduct().equals(product))
                .collect(Collectors.toList()).get(0).getQuantity();
        assertThat(actualItemsForProduct).isEqualTo(1);
        assertThat(actualQuantityForProduct).isEqualTo(expectedQuantity);

    }

    @Test
    void shoppingCartTest_addExistingProduct_shouldAddQuantityToCart() {
        // Given
        ShoppingCart shoppingCart = new ShoppingCart(CustomerFixture.aLegalCustomer());
        Product product = ProductFixture.aSmartphonePremium();
        int expectedQuantity1= 1;
        int expectedQuantity2= 2;
        int totalExpectedQuantity = expectedQuantity1 + expectedQuantity2;
        // When
        shoppingCart.addProduct(product, expectedQuantity1);
        shoppingCart.addProduct(product, expectedQuantity2);
        // Then
        int actualItemsForProduct = (int) shoppingCart.getLineItems().stream()
                .filter(cartLineItem -> cartLineItem.getProduct().equals(product)).count();
        int actualQuantityForProduct = shoppingCart.getLineItems().stream()
                .filter(cartLineItem -> cartLineItem.getProduct().equals(product))
                .collect(Collectors.toList()).get(0).getQuantity();
        assertThat(actualItemsForProduct).isEqualTo(1);
        assertThat(actualQuantityForProduct).isEqualTo(totalExpectedQuantity);
    }


}
