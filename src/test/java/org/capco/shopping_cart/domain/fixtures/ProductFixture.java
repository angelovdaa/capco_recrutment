package org.capco.shopping_cart.domain.fixtures;

import org.capco.shopping_cart.domain.entities.id.UUIDGenerator;
import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.entities.product.ProductType;

import java.util.List;
import java.util.Random;

public class ProductFixture {

    public static Product aSmartphonePremium() {
        return new Product(UUIDGenerator.generate(), "IPhone15", ProductType.SMARTPHONE_PREMIUM, "Most recent IPhone model");
    }

    public static Product aSmartphoneStandard() {
        return new Product(UUIDGenerator.generate(), "Samsung Galaxy", ProductType.SMARTPHONE_STANDARD, "Middle range Samsung Galaxy model");
    }

    public static Product aLaptop() {
        return new Product(UUIDGenerator.generate(), "MacBook", ProductType.LAPTOP, "Apple MacBook Pro 2021");
    }

    public static Product random() {
        List<Product> productList = List.of(ProductFixture.aLaptop(), ProductFixture.aSmartphoneStandard(), ProductFixture.aSmartphonePremium());
        Random random = new Random();
        return productList.get(random.nextInt(productList.size()));

    }

}
