package org.capco.shopping_cart.domain.entities;

import org.capco.shopping_cart.domain.entities.cart.CartLineItem;
import org.capco.shopping_cart.domain.entities.id.UUIDGenerator;
import org.capco.shopping_cart.domain.fixtures.ProductFixture;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CartLineItemTest {

    @Test
    void test_zeroQuantity_shouldThrowIlleagalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new CartLineItem(UUIDGenerator.generate(), ProductFixture.aSmartphonePremium(), 0));
    }

    @Test
    void test_increaseByPositiveQuantity_shouldReturnQuantityIncreased() {
        // Given
        Random rndNumber = new Random();
        int initialQuantity = rndNumber.nextInt(5) + 1;
        int increaseByQuantity = rndNumber.nextInt(5) + 1;
        int expectedQuantity = initialQuantity + increaseByQuantity;
        CartLineItem cartLineItem = new CartLineItem(UUIDGenerator.generate(), ProductFixture.aSmartphonePremium(), initialQuantity);
        // When
        cartLineItem.increaseQuantityBy(increaseByQuantity);
        // Then
        assertThat(cartLineItem.getQuantity()).isEqualTo(expectedQuantity);
    }

    @Test
    void test_increaseByZeroQuantity_shouldThrowIllegalArgumentException() {
        // Given
        Random rndNumber = new Random();
        int initialQuantity = rndNumber.nextInt(5) + 1;
        int increaseByQuantity = 0;
        CartLineItem cartLineItem = new CartLineItem(UUIDGenerator.generate(), ProductFixture.aSmartphonePremium(), initialQuantity);
        // Then
        assertThrows(IllegalArgumentException.class, () -> cartLineItem.increaseQuantityBy(increaseByQuantity));
    }
}
