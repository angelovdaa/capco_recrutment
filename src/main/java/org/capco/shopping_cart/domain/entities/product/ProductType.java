package org.capco.shopping_cart.domain.entities.product;

public enum ProductType {
    SMARTPHONE_PREMIUM(0),
    SMARTPHONE_STANDARD(1),
    LAPTOP(2);

    private final int type;

    private ProductType(int type) {
        this.type = type;
    }
}
