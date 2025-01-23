package org.capco.shopping_cart.infrastructure.database.price;

import org.capco.shopping_cart.domain.entities.id.UUIDGenerator;
import org.capco.shopping_cart.domain.entities.product.ProductType;
import org.capco.shopping_cart.domain.use_cases.price.PricePort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.OptionalDouble;

@Component
public class PriceDatabaseAdapter implements PricePort {

    private final PriceJPARepository priceJPARepository;

    public PriceDatabaseAdapter(PriceJPARepository priceJPARepository) {
        this.priceJPARepository = priceJPARepository;
    }

    @Override
    public OptionalDouble getForProductType(ProductType productType, String currencyCode) {
        List<PriceJPA> prices = priceJPARepository.findByProductTypeAndCurrencyCode(productType, currencyCode);
        if (prices.isEmpty()) {
            return OptionalDouble.empty();
        }
        return OptionalDouble.of(prices.get(0).getPrice());
    }

    @Override
    public void save(ProductType productType, double price, String currencyCode) {
        List<PriceJPA> prices = priceJPARepository.findByProductTypeAndCurrencyCode(productType, currencyCode);
        if (prices.isEmpty()) {
            PriceJPA priceJPA = new PriceJPA();
            priceJPA.setId(UUIDGenerator.generate());
            priceJPA.setProductType(productType);
            priceJPA.setPrice(price);
            priceJPA.setCurrencyCode(currencyCode);
            priceJPARepository.save(priceJPA);
        }
        PriceJPA priceJPA = prices.get(0);
        priceJPA.setPrice(price);
        priceJPARepository.save(priceJPA);
    }
}
