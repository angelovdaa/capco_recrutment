package org.capco.shopping_cart.domain.entities.cart;

import org.capco.shopping_cart.domain.entities.product.Product;

/**
 * Simple shopping cart line item with a product and quantity.
 * TODO: Add use case to manage available stock / inventory
 */
public class CartLineItem {

    private final String id;
    private final Product product;
    private int quantity;

    public CartLineItem(String id, Product product, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Cart line item must have at least one item");
        }
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantityBy(int additionalQuantity) {
        if (additionalQuantity < 1) {
            throw new IllegalArgumentException("You must add at least one item");
        }
        this.quantity += additionalQuantity;
    }


}
