package org.capco.shopping_cart.domain.entities.price;

public enum PriceType {
    BASIC(0),
    ANNUAL_REVENUE_LESS_THAN_EQ_10M(1),
    ANNUAL_REVENUE_MORE_THAN_10M(2);

    private final int type;

    PriceType(int type) {
        this.type = type;
    }
}
