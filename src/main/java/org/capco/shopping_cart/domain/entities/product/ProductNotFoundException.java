package org.capco.shopping_cart.domain.entities.product;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
