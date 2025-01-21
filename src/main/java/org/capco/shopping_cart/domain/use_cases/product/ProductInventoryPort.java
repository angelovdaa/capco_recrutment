package org.capco.shopping_cart.domain.use_cases.product;

import org.capco.shopping_cart.domain.entities.product.Product;

public interface ProductInventoryPort {
    int getInventory(Product product);

    void updateInventory(Product product, int quantity);
}
