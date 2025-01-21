package org.capco.shopping_cart.infrastructure.database.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  ProductJPARepository extends JpaRepository<ProductJPA, String> {
    Optional<ProductJPA> findByProductId(String productId);

}
