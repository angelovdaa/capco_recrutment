package org.capco.shopping_cart.infrastructure.database.price;


import org.capco.shopping_cart.domain.entities.product.ProductType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product_price", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"productType", "currencyCode"})
})
public class PriceJPA {

    @Id
    String id;


    @Enumerated(EnumType.STRING)
    @NotNull
    private ProductType productType;

//    @Enumerated(EnumType.STRING)
//    @NotNull
//    private PriceType priceType;

    private double price;

    private String currencyCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
