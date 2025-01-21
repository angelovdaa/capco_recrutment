package org.capco.shopping_cart.infrastructure.database.customer;

import org.capco.shopping_cart.domain.entities.customer.LegalCustomer;
import org.capco.shopping_cart.domain.entities.product.Money;
import org.capco.shopping_cart.domain.use_cases.customer.AnnualRevenuePort;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Random;

public class RandomAnnualRevenueInEuroAdapter implements AnnualRevenuePort {

    @Override
    public Money get(LegalCustomer customer) {
        BigDecimal revenue = BigDecimal.valueOf(Math.round(Math.random() * 20000000));
        return Money.euros(revenue);
    }
}
