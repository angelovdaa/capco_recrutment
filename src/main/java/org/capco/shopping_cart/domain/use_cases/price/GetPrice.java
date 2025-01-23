package org.capco.shopping_cart.domain.use_cases.price;

import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.product.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.OptionalDouble;

@Component
public class GetPrice {

    private final PricePort pricePort;

    private final GetDiscounts getDiscounts;

    public GetPrice(PricePort pricePort, GetDiscounts getDiscounts) {
        this.pricePort = pricePort;
        this.getDiscounts = getDiscounts;
    }

    public OptionalDouble execute(Customer customer, Product product, String currencyCode) {
        OptionalDouble basicPrice = pricePort.getForProductType(product.getProductType(), currencyCode);
        if (basicPrice.isPresent()) {
            double price = basicPrice.getAsDouble();
            List<Double> discounts = getDiscounts.execute(customer, product);
            for (int i = 0; i < discounts.size(); i++) {
                price *= (1 - discounts.get(i));
            }
            return OptionalDouble.of(price);
        }
        return OptionalDouble.empty();

    }
}
