package org.capco.shopping_cart.infrastructure.database.product;

import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.use_cases.product.ProductInventoryPort;

public class UnlimitedProductInventoryAdapter implements ProductInventoryPort {
    @Override
    public int getInventory(Product product) {
        return Integer.MAX_VALUE;
    }

    @Override
    public void updateInventory(Product product, int quantity) {
        // do nothing
    }
}
