package org.capco.shopping_cart.domain.use_cases.price;

import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.customer.CustomerType;
import org.capco.shopping_cart.domain.entities.customer.LegalCustomer;
import org.capco.shopping_cart.domain.entities.price.PriceType;
import org.capco.shopping_cart.domain.entities.product.Money;
import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.use_cases.customer.AnnualRevenuePort;

public class GetPriceByAnnualRevenue implements GetPrice {

    private static final int TEN_MILLION = 10000000;

    private final AnnualRevenuePort annualRevenuePort;

    private final ProductPricePort productPricePort;

    public GetPriceByAnnualRevenue(AnnualRevenuePort annualRevenuePort, ProductPricePort productPricePort) {
        this.annualRevenuePort = annualRevenuePort;
        this.productPricePort = productPricePort;
    }

    @Override
    public Money get(Customer customer, Product product) {
        if (customer.getCustomerType().equals(CustomerType.LEGAL)) {

            double revenue = annualRevenuePort.get((LegalCustomer) customer).getAmount().doubleValue();
            return productPricePort.get(product,
                    revenue <= TEN_MILLION ? PriceType.ANNUAL_REVENUE_LESS_THAN_EQ_10M : PriceType.ANNUAL_REVENUE_MORE_THAN_10M);
        }
        return productPricePort.get(product, PriceType.BASIC);
    }
}
