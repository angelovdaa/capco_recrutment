package org.capco.shopping_cart.infrastructure.database.price;


import org.capco.shopping_cart.domain.entities.price.PriceType;
import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.infrastructure.database.product.ProductJPA;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Currency;

@Entity
@Table(name = "product_price",uniqueConstraints= {
        @UniqueConstraint(columnNames = {"productId","priceType"})
})
public class ProductPriceJPA {

    @Id
    String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private ProductJPA product;

    private PriceType priceType;

    private double price;

    private  String currencyCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductJPA getProduct() {
        return product;
    }

    public void setProduct(ProductJPA product) {
        this.product = product;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
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
