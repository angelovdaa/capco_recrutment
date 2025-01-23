package org.capco.shopping_cart.infrastructure.database.product;

import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.entities.product.ProductType;
import org.capco.shopping_cart.domain.use_cases.product.ProductPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDatabaseAdapter implements ProductPort {

    private final ProductJPARepository productJPARepository;

    public ProductDatabaseAdapter(ProductJPARepository productJPARepository) {
        this.productJPARepository = productJPARepository;
    }

    @Override
    public List<Product> getByNameContainsIgnoreCase(String name) {
        return productJPARepository.findByNameContainsIgnoreCase(name)
                .stream()
                .map(this::toProduct)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getByProductType(ProductType productType) {
        return productJPARepository.findByProductType(productType)
                .stream()
                .map(this::toProduct)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Product product) {
        productJPARepository.save(fromProduct(product));
    }

    private Product toProduct(ProductJPA productJPA) {
        return new Product(productJPA.getId(), productJPA.getName(), productJPA.getProductType(), productJPA.getDescription());
    }

    private ProductJPA fromProduct(Product product) {
        ProductJPA productJPA = new ProductJPA();
        productJPA.setId(product.getId());
        productJPA.setName(product.getName());
        productJPA.setProductType(product.getProductType());
        productJPA.setDescription(product.getDescription());
        return productJPA;
    }
}
