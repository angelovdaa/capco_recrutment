package org.capco.shopping_cart.domain.use_cases.shopping_cart;

import org.capco.shopping_cart.domain.entities.cart.ShoppingCart;
import org.capco.shopping_cart.domain.entities.customer.Customer;

import java.util.Optional;

public interface ShoppingCartPort {
    Optional<ShoppingCart> getForCustomer(Customer customer);

    void emptyCart(Customer customer);

    void save(ShoppingCart shoppingCart);
}
