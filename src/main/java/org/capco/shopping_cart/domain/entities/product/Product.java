package org.capco.shopping_cart.domain.entities.product;

import java.util.Objects;

public class Product {
    private final String id;
    private final String name;

    private final ProductType productType;
    private String description;

    public Product(String id, String name, ProductType productType, String description) {
        this.id = id;
        this.name = name;
        this.productType = productType;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public ProductType getProductType() {
        return productType;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productType, product.getProductType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(productType);
    }
}
