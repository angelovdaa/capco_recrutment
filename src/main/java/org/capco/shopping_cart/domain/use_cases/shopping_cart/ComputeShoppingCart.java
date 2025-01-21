package org.capco.shopping_cart.domain.use_cases.shopping_cart;

import org.capco.shopping_cart.domain.entities.cart.ShoppingCart;
import org.capco.shopping_cart.domain.entities.cart.CartLineItem;
import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.product.Money;
import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.use_cases.price.GetPrice;
import org.capco.shopping_cart.domain.use_cases.price.ProductPricePort;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class ComputeShoppingCart {
    private final ShoppingCartPort shoppingCartPort;

    private final GetPrice getPrice;

    public ComputeShoppingCart(ShoppingCartPort shoppingCartPort, GetPrice getPrice) {
        this.shoppingCartPort = shoppingCartPort;
        this.getPrice = getPrice;
    }

    public Money compute(Customer customer) {

        Optional<ShoppingCart> shoppingCart = shoppingCartPort.getForCustomer(customer);
        if (shoppingCart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }
        List<CartLineItem> items = shoppingCart.get().getLineItems();
        ShoppingCart cart = shoppingCart.get();

        Iterator<CartLineItem> iterator = items.iterator();
        CartLineItem item = iterator.next();
        Product product = item.getProduct();
        Money itemPrice = getPrice.get(customer, product);
        Money amount = itemPrice.multiply(item.getQuantity());
        while (iterator.hasNext()) {
            item = iterator.next();
            itemPrice = getPrice.get(customer, product);
            amount = amount.add(itemPrice.multiply(item.getQuantity()));
        }
        return amount;
    }
}
