package org.capco.shopping_cart.domain.fixtures;

import org.capco.shopping_cart.domain.entities.id.UUIDGenerator;
import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.entities.product.ProductType;

public class ProductFixture {

    public static Product aSmartphonePremium() {
        return new Product(UUIDGenerator.generate(), UUIDGenerator.generate(), "IPhone15", ProductType.SMARTPHONE_PREMIUM, "Most recent IPhone model");
    }

    public static Product aSmartphoneStandart() {
        return new Product(UUIDGenerator.generate(),UUIDGenerator.generate(), "Samsung Galaxy", ProductType.SMARTPHONE_STANDARD, "Middle range Samsung Galaxy model");
    }

    public static Product aLaptop() {
        return new Product(UUIDGenerator.generate(),UUIDGenerator.generate(), "MacBook", ProductType.LAPTOP, "Apple MacBook Pro 2021");
    }

}
