package org.capco.shopping_cart.infrastructure.database.price;

import org.capco.shopping_cart.domain.entities.price.PriceType;
import org.capco.shopping_cart.domain.entities.price.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPriceJPA, String> {
    Optional<ProductPriceJPA> findByProductIdAndPriceType(String productId, PriceType priceType);
}
