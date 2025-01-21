package org.capco.shopping_cart.domain.use_cases.price;

import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.product.Money;
import org.capco.shopping_cart.domain.entities.product.Product;

public interface GetPrice {

    Money get(Customer customer, Product product);



}
