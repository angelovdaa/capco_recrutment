package org.capco.shopping_cart.infrastructure.database.price;

import org.capco.shopping_cart.domain.entities.price.PriceType;
import org.capco.shopping_cart.domain.entities.product.Money;
import org.capco.shopping_cart.domain.entities.product.Product;
import org.capco.shopping_cart.domain.use_cases.price.ProductPricePort;

import java.util.Optional;

public class ProductPriceDatabaseAdapter implements ProductPricePort {

    private final ProductPriceRepository productPriceRepository;

    public ProductPriceDatabaseAdapter(ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    @Override
    public Money get(Product product, PriceType priceType) {
        Optional<ProductPriceJPA> pricePriceJPAOptional = productPriceRepository.findByProductIdAndPriceType(product.getProductId(), priceType);
//        if(pricePriceJPAOptional.isPresent()){
//            ProductPriceJPA productPriceJPA = pricePriceJPAOptional.get();
//
//        }
        return null;

    }
}
