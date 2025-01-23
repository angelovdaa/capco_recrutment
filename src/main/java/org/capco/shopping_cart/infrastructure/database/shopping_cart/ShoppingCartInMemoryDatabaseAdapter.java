package org.capco.shopping_cart.infrastructure.database.shopping_cart;

import org.capco.shopping_cart.domain.entities.cart.ShoppingCart;
import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.use_cases.shopping_cart.ShoppingCartPort;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ShoppingCartInMemoryDatabaseAdapter implements ShoppingCartPort {

    private final Map<Customer, ShoppingCart> shoppingCarts = new ConcurrentHashMap<>();

    @Override
    public void emptyCart(Customer customer) {
        shoppingCarts.remove(customer);
    }

    @Override
    public void save(ShoppingCart shoppingCart) {
        shoppingCarts.put(shoppingCart.getCustomer(), shoppingCart);
    }

    @Override
    public ShoppingCart getForCustomer(Customer customer) {
        if (shoppingCarts.containsKey(customer)) {
            return shoppingCarts.get(customer);
        }
        return new ShoppingCart(customer);
    }
}
