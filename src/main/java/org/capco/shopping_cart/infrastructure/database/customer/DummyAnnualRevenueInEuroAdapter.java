package org.capco.shopping_cart.infrastructure.database.customer;

import org.capco.shopping_cart.domain.entities.customer.LegalCustomer;
import org.capco.shopping_cart.domain.entities.product.Money;
import org.capco.shopping_cart.domain.use_cases.customer.AnnualRevenuePort;
import org.springframework.stereotype.Component;

@Component
public class DummyAnnualRevenueInEuroAdapter implements AnnualRevenuePort {

    private static final int HUNDRED_MILLION = 100000000;
    private static final int ONE_MILLION = 1000000;

    @Override
    public Money getForCustomer(LegalCustomer customer) {
        return customer.getCompanyName().contains("Capco") ? Money.euros(HUNDRED_MILLION) : Money.euros(ONE_MILLION);
    }
}
