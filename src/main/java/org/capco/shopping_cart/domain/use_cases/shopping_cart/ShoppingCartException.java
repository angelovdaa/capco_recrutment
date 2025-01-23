package org.capco.shopping_cart.domain.use_cases.shopping_cart;

public class ShoppingCartException extends RuntimeException {
    public ShoppingCartException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShoppingCartException(String message) {
        super(message);
    }
}
