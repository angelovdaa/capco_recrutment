package org.capco.shopping_cart.infrastructure.database.shopping_cart;

import org.capco.shopping_cart.domain.entities.cart.ShoppingCart;
import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.use_cases.shopping_cart.ShoppingCartPort;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class RandomShoppingCartInMemoryDatabaseAdapter implements ShoppingCartPort {

    private final Map<String, ShoppingCart> shoppingCarts = new ConcurrentHashMap<>();


    @Override
    public void emptyCart(Customer customer) {
        shoppingCarts.remove(customer.getId());
    }

    @Override
    public void save(ShoppingCart shoppingCart) {
        shoppingCarts.put(shoppingCart.getCustomer().getId(), shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> getForCustomer(Customer customer) {
        return Optional.ofNullable(shoppingCarts.get(customer.getId()));
    }
}
