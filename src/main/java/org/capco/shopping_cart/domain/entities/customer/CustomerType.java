package org.capco.shopping_cart.domain.entities.customer;

public enum CustomerType {
    NATURAL(0),
    LEGAL(1);

    private final int type;

    private CustomerType(int type) {
        this.type = type;
    }

}
