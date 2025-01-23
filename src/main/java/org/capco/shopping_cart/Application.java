package org.capco.shopping_cart;

import org.capco.shopping_cart.domain.entities.cart.ShoppingCart;
import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.entities.product.ProductType;
import org.capco.shopping_cart.domain.use_cases.shopping_cart.ComputeShoppingCart;
import org.capco.shopping_cart.infrastructure.database.customer.CustomerDatabaseAdapter;
import org.capco.shopping_cart.infrastructure.database.product.ProductDatabaseAdapter;
import org.capco.shopping_cart.infrastructure.database.shopping_cart.ShoppingCartInMemoryDatabaseAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Currency;
import java.util.Optional;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        // Customer
        CustomerDatabaseAdapter customerDatabaseAdapter = ctx.getBean(CustomerDatabaseAdapter.class);
        Optional<Customer> customer = customerDatabaseAdapter.getBySiren("00000000000001");// Capco LTD;


        ProductDatabaseAdapter productDatabaseAdapter = ctx.getBean(ProductDatabaseAdapter.class);
        Product laptop = productDatabaseAdapter.getByProductType(ProductType.LAPTOP).get(0);
        Product premiumPhone = productDatabaseAdapter.getByProductType(ProductType.SMARTPHONE_PREMIUM).get(0);

        // Add 2 laptops and  3  premium smartphones to cart
        ShoppingCartInMemoryDatabaseAdapter shoppingCartInMemoryDatabaseAdapter = ctx.getBean(ShoppingCartInMemoryDatabaseAdapter.class);
        ShoppingCart shoppingCart = shoppingCartInMemoryDatabaseAdapter.getForCustomer(customer.orElseThrow(() -> new IllegalArgumentException("CustomerNotFound")));
        shoppingCart.addProduct(laptop, 2);
        shoppingCart.addProduct(premiumPhone, 3);
        shoppingCartInMemoryDatabaseAdapter.save(shoppingCart);

        // Total amount
        System.out.println(shoppingCart.toDescription());
        ComputeShoppingCart computeShoppingCart = ctx.getBean(ComputeShoppingCart.class);
        System.out.println(computeShoppingCart.execute(customer.get(), Currency.getInstance("EUR")));
    }
}