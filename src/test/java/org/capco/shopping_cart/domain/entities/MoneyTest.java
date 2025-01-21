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
    void additionTest() {
        // Given
        Random rndNumber = new Random();
        double d1 = rndNumber.nextDouble();
        double d2 = rndNumber.nextDouble();
        BigDecimal amount1 = new BigDecimal(d1);
        BigDecimal amount2 = new BigDecimal(d2);
        Money money1 = Money.euros(amount1);
        Money money2 = Money.euros(amount2);
        // When
        Money actual = Money.euros(money1.getAmount().add(money2.getAmount()));
        Money expected = money1.add(money2);
        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void multiplicationTest() {
        // Given
        Random rndNumber = new Random();
        double d1 = rndNumber.nextDouble();
        double factor = rndNumber.nextDouble();
        BigDecimal amount1 = new BigDecimal(d1);
        Money money1 =  Money.euros(amount1);
        // When
        Money actual = Money.euros(money1.getAmount().multiply(BigDecimal.valueOf(factor)));
        Money expected = money1.multiply(factor);
        // Then
        assertThat(actual).isEqualTo(expected);

    }
}
