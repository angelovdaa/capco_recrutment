package org.capco.shopping_cart.domain.use_cases.shopping_cart;

import org.capco.shopping_cart.domain.entities.cart.CartLineItem;
import org.capco.shopping_cart.domain.entities.cart.ShoppingCart;
import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.product.Money;
import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.use_cases.price.GetPrice;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.List;
import java.util.OptionalDouble;

@Component
public class ComputeShoppingCart {
    private final ShoppingCartPort shoppingCartPort;

    private final GetPrice getPrice;


    public ComputeShoppingCart(ShoppingCartPort shoppingCartPort, GetPrice getPrice) {
        this.shoppingCartPort = shoppingCartPort;
        this.getPrice = getPrice;
    }

    public Money execute(Customer customer, Currency currency) throws ShoppingCartException {
        ShoppingCart shoppingCart = shoppingCartPort.getForCustomer(customer);
        if (shoppingCart.isEmpty()) {
            throw new ShoppingCartException("Empty cart", null);
        }
        List<CartLineItem> items = shoppingCart.getLineItems();
        Money totalAMount = Money.euros(0d);
        for (CartLineItem item : items) {
            Product product = item.getProduct();
            OptionalDouble price = getPrice.execute(customer, product, currency.getCurrencyCode());
            if (price.isEmpty()) {
                throw new ShoppingCartException("Unknown price for product " + product.getName());
            }
            Money itemAmount = Money.fromAmountCurrency(price.getAsDouble(), currency).multiply(item.getQuantity());
            totalAMount = totalAMount.add(itemAmount);
        }
        return totalAMount;
    }
}
