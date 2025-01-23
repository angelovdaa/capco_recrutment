package org.capco.shopping_cart.domain.use_cases.price;

import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.product.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetDiscounts {

    private final DiscountPort discountPort;

    public GetDiscounts(DiscountPort discountPort) {
        this.discountPort = discountPort;
    }

    public List<Double> execute(Customer customer, Product product) {
        return discountPort.getForCustomerProduct(customer, product);


    }
}
