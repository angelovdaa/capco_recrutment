package org.capco.shopping_cart.domain.use_cases.price;

import org.capco.shopping_cart.domain.entities.price.PriceType;
import org.capco.shopping_cart.domain.entities.product.Money;
import org.capco.shopping_cart.domain.entities.product.Product;

/**
 * Use case for getting the price of a product in Euro for a Legal or Natural Customer
 */
public interface ProductPricePort {

//    private static final Currency EURO = Currency.getInstance("EUR");

//    LegalCustomerRevenuePort legalCustomerRevenuePort;


    Money get(Product product, PriceType priceType);
//
//    public Money getPrice(Product product, Customer customer) {
//        CustomerType customerType = customer.getCustomerType();
//        ProductType productType = product.getProductType();
//        if (CustomerType.NATURAL.equals(customerType)) {
//            if (ProductType.SMARTPHONE_PREMIUM.equals(productType)) {
//                return new Money(EURO, BigDecimal.valueOf(1500.0));
//            } else if (ProductType.SMARTPHONE_STANDARD.equals(productType)) {
//                return new Money(EURO, BigDecimal.valueOf(800.0));
//            } else if (ProductType.LAPTOP.equals(productType)) {
//                return new Money(EURO, BigDecimal.valueOf(550.0));
//            } else {
//                throw new IllegalArgumentException("Product type not supported");
//            }
//        } else if (CustomerType.LEGAL.equals(customerType)) {
//
//            Money annualRevenue = legalCustomerRevenuePort.getAnnualRevenue((LegalCustomer) customer);
//            boolean lessThan10M = annualRevenue.getAmount().compareTo(BigDecimal.valueOf(10000000.0)) < 0;
//
//            if (ProductType.SMARTPHONE_PREMIUM.equals(productType)) {
//                return new Money(EURO, lessThan10M ? BigDecimal.valueOf(1150.0) : BigDecimal.valueOf(1500.0));
//            } else if (ProductType.SMARTPHONE_STANDARD.equals(productType)) {
//                return new Money(EURO, lessThan10M ? BigDecimal.valueOf(600.0) : BigDecimal.valueOf(550.0));
//            } else if (ProductType.LAPTOP.equals(productType)) {
//                return new Money(EURO, lessThan10M ? BigDecimal.valueOf(1000.0) : BigDecimal.valueOf(900.0));
//            } else {
//                throw new IllegalArgumentException("Product type not supported");
//            }
//        } else {
//            throw new IllegalArgumentException("Customer type not supported");
//        }
}





