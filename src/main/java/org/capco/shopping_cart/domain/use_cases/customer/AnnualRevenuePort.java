package org.capco.shopping_cart.domain.use_cases.customer;

import org.capco.shopping_cart.domain.entities.customer.LegalCustomer;
import org.capco.shopping_cart.domain.entities.product.Money;

public interface AnnualRevenuePort {

    public Money get(LegalCustomer customer);

}
