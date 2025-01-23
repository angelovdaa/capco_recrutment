package org.capco.shopping_cart.infrastructure.database.price;

import org.capco.shopping_cart.domain.entities.product.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceJPARepository extends JpaRepository<PriceJPA, String> {
    List<PriceJPA> findByProductTypeAndCurrencyCode(ProductType productType, String currencyCode);
}
