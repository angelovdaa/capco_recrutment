package org.capco.shopping_cart.domain.entities.cart;

import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.id.UUIDGenerator;
import org.capco.shopping_cart.domain.entities.product.Product;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    // One customer by cart
    private final Customer customer;

    // Product to quantity mapping
    private final Map<Product, CartLineItem> lineItems = Collections.synchronizedMap(new LinkedHashMap<>());

    public ShoppingCart(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void addProduct(Product product, int quantity) {
        CartLineItem cartLineItem = lineItems.get(product);
        if (cartLineItem != null) {
            cartLineItem.increaseQuantityBy(quantity);
            return;
        }
        lineItems.put(product, new CartLineItem(UUIDGenerator.generate(), product, quantity));
    }

    public List<CartLineItem> getLineItems() {
        return List.copyOf(lineItems.values());
    }

    public boolean isEmpty() {
        return lineItems.isEmpty();
    }

    public String toDescription() {
        StringBuilder description = new StringBuilder();
        description.append("Shopping cart for customer: ").append(getCustomer().toDescription());
        description.append("\n");
        getLineItems().forEach(lineItem -> {
            description.append("Product: ").append(lineItem.getProduct().getProductType()).append(" quantity: ").append(lineItem.getQuantity());
            description.append("\n");
        });

        return description.toString();

    }

}
