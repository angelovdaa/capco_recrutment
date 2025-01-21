package org.capco.shopping_cart.domain.use_cases.shopping_cart;

import org.capco.shopping_cart.domain.entities.cart.ShoppingCart;
import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.product.NotEnoughItemsInStockException;
import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.entities.product.ProductNotFoundException;
import org.capco.shopping_cart.domain.use_cases.product.ProductInventoryPort;

public class AddToShoppingCart {

    private final ShoppingCartPort shoppingCartPort;
    private final ProductInventoryPort productInventoryPort;

    public AddToShoppingCart(ShoppingCartPort shoppingCartPort, ProductInventoryPort productInventoryPort) {
        this.shoppingCartPort = shoppingCartPort;
        this.productInventoryPort = productInventoryPort;
    }

    ShoppingCart add(Customer customer, Product product, int quantity) throws NotEnoughItemsInStockException {
        ShoppingCart shoppingCart = shoppingCartPort.getForCustomer(customer).orElse(new ShoppingCart(customer));
        int availableQuantity = productInventoryPort.getInventory(product);
        if (quantity > availableQuantity) {
            throw new NotEnoughItemsInStockException("Product available quantity is: " + availableQuantity, null);
        }
        shoppingCart.addProduct(product, quantity);
        shoppingCartPort.save(shoppingCart);
        return shoppingCart;
    }
}

