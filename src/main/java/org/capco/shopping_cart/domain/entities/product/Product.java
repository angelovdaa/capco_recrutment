package org.capco.shopping_cart.domain.entities.product;

import java.util.Objects;

public class Product {

    private final String id;
    private final String name;

    private final String productId;

    private final ProductType productType;
    private String description;

    public Product(String id, String name, String productId, ProductType productType, String description) {
        this.id = id;
        this.name = name;
        this.productId = productId;
        this.productType = productType;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProductId() {
        return productId;
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
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
