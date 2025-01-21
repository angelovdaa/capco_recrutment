package org.capco.shopping_cart.domain.entities.product;

public enum ProductType {
    SMARTPHONE_PREMIUM(0),
    SMARTPHONE_STANDARD(2),
    LAPTOP(3);

    public final int type;

    private ProductType(int type) {
        this.type = type;
    }
}
