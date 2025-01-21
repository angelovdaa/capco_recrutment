package org.capco.shopping_cart.domain.entities.product;

public class NotEnoughItemsInStockException extends Exception {
    public NotEnoughItemsInStockException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
