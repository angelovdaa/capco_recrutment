package org.capco.shopping_cart.infrastructure.database.product;

import org.capco.shopping_cart.domain.entities.product.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJPARepository extends JpaRepository<ProductJPA, String> {
    List<ProductJPA> findByNameContainsIgnoreCase(String name);

    List<ProductJPA> findByProductType(ProductType productType);
}
