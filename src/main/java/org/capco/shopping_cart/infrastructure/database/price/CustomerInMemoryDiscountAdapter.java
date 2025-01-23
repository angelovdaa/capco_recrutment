package org.capco.shopping_cart.infrastructure.database.price;

import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.customer.CustomerType;
import org.capco.shopping_cart.domain.entities.customer.LegalCustomer;
import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.entities.product.ProductType;
import org.capco.shopping_cart.domain.use_cases.customer.AnnualRevenuePort;
import org.capco.shopping_cart.domain.use_cases.price.DiscountPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerInMemoryDiscountAdapter implements DiscountPort {

    private static final int TEN_MILLION = 10000000;
    private final AnnualRevenuePort annualRevenuePort;

    public static final double ZERO_DISCOUNT = 0d;
    public static final double DISCOUNT_LAPTOP_LESS_EQ_10M = 0.16;
    public static final double DISCOUNT_LAPTOP_MORE_THAN_10M = 0.25;
    public static final double DISCOUNT_SMARTPHONE_PREMIUM_LESS_EQ_10M = 0.50;
    public static final double DISCOUNT_SMARTPHONE_PREMIUM_MORE_THAN_10M = 0.23;

    public static final double DISCOUNT_SMARTPHONE_STANDARD_LESS_EQ_10M = 0.31;
    public static final double DISCOUNT_SMARTPHONE_STANDARD_MORE_THAN_10M = 0.25;


    public CustomerInMemoryDiscountAdapter(AnnualRevenuePort annualRevenuePort) {
        this.annualRevenuePort = annualRevenuePort;
    }

    @Override
    public List<Double> getForCustomerProduct(Customer customer, Product product) {
        double discount = 0d;
        if (CustomerType.NATURAL.equals(customer.getCustomerType())) {
            return List.of(ZERO_DISCOUNT);
        }

        double revenue = annualRevenuePort.getForCustomer((LegalCustomer) customer).getAmount().doubleValue();
        boolean lessThanEq10M = (revenue <= TEN_MILLION);
        ProductType productType = product.getProductType();
        if (ProductType.LAPTOP.equals(productType)) {
            discount = lessThanEq10M ? DISCOUNT_LAPTOP_LESS_EQ_10M : DISCOUNT_LAPTOP_MORE_THAN_10M;
        }
        if (ProductType.SMARTPHONE_PREMIUM.equals(productType)) {
            discount = lessThanEq10M ? DISCOUNT_SMARTPHONE_PREMIUM_LESS_EQ_10M : DISCOUNT_SMARTPHONE_PREMIUM_MORE_THAN_10M;
        }
        if (ProductType.SMARTPHONE_STANDARD.equals(productType)) {
            discount = lessThanEq10M ? DISCOUNT_SMARTPHONE_STANDARD_LESS_EQ_10M : DISCOUNT_SMARTPHONE_STANDARD_MORE_THAN_10M;
        }
        return List.of(discount);
    }


}
