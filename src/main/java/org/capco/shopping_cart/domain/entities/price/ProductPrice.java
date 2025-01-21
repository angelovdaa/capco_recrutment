package org.capco.shopping_cart.domain.entities.price;

import org.capco.shopping_cart.domain.entities.product.Product;

import java.util.Currency;

public class ProductPrice {

    private final Product product;

    private final PriceType priceType;

    private final double price;

    private final Currency currency;

    public ProductPrice(Product product, PriceType priceType, double price, Currency currency) {
        this.product = product;
        this.priceType = priceType;
        this.price = price;
        this.currency = currency;
    }

    public Product getProduct() {
        return product;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public double getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }
}
