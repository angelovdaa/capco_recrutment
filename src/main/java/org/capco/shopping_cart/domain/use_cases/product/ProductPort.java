package org.capco.shopping_cart.domain.use_cases.product;

import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.entities.product.ProductType;

import java.util.List;

public interface ProductPort {

    List<Product> getByNameContainsIgnoreCase(String name);

    List<Product> getByProductType(ProductType productType);

    void save(Product product);
}
