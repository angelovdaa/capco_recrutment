package org.capco.shopping_cart.domain.entities;

import org.capco.shopping_cart.domain.entities.product.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {

    private final Currency EURO = Currency.getInstance("EUR");

    @Test
    void test_AddMoney() {
        // Given
        Random rndNumber = new Random();
        double d1 = rndNumber.nextDouble();
        double d2 = rndNumber.nextDouble();
        Money money1 = Money.euros(d1);
        Money money2 = Money.euros(d2);
        // When
        Money actual = Money.euros(money1.getAmount().add(money2.getAmount()).doubleValue());
        Money expected = money1.add(money2);
        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test_multiplyMoneyByFactor() {
        // Given
        Random rndNumber = new Random();
        double d1 = rndNumber.nextDouble();
        double factor = rndNumber.nextDouble();
        Money money1 = Money.euros(d1);
        // When
        Money actual = Money.euros(money1.getAmount().multiply(BigDecimal.valueOf(factor)).doubleValue());
        Money expected = money1.multiply(factor);
        // Then
        assertThat(actual).isEqualTo(expected);

    }
}
