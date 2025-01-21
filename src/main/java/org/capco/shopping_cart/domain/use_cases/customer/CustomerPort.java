package org.capco.shopping_cart.domain.use_cases.customer;

import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.customer.LegalCustomer;

import java.util.List;

public interface CustomerPort {

    List<Customer> getByName(String name);

    List<LegalCustomer> getBySiren(String companyID);

    void save(Customer customer);
}
