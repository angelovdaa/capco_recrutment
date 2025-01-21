package org.capco.shopping_cart.infrastructure.database.product;

import org.capco.shopping_cart.domain.entities.product.ProductType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="product",uniqueConstraints= {
        @UniqueConstraint(columnNames = {"productId"})
})
public class ProductJPA {
    @Id
    private String id;

    private  String name;

    private  String productId;

    private  ProductType productType;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
