package org.capco.shopping_cart.domain.use_cases.customer;

import org.capco.shopping_cart.domain.entities.customer.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerPort {

    List<Customer> getByNameContainsIgnoreCase(String name);

    Optional<Customer> getBySiren(String companyID);

    void save(Customer customer);
}
