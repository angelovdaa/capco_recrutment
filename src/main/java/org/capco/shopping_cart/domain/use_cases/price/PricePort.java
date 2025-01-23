package org.capco.shopping_cart.domain.use_cases.price;

import org.capco.shopping_cart.domain.entities.product.ProductType;

import java.util.OptionalDouble;

public interface PricePort {

    OptionalDouble getForProductType(ProductType productType, String currencyCode);

    void save(ProductType productType, double price, String currencyCode);
}





