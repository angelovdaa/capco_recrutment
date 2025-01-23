package org.capco.shopping_cart.domain.use_cases.price;

import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.product.Product;

import java.util.List;

public interface DiscountPort {

    List<Double> getForCustomerProduct(Customer customer, Product product);
}
