package org.capco.shopping_cart.domain.fixtures;

import org.capco.shopping_cart.domain.entities.id.UUIDGenerator;
import org.capco.shopping_cart.domain.entities.cart.CartLineItem;

import java.util.List;
import java.util.Random;

public class CartLineItemFixture {
    private static final Random rndNumber = new Random();
    private static final List<CartLineItem> PHONE_ITEMS = List.of(
            new CartLineItem(UUIDGenerator.generate(), ProductFixture.aSmartphonePremium(), rndNumber.nextInt(5)),
            new CartLineItem(UUIDGenerator.generate(), ProductFixture.aSmartphoneStandart(), rndNumber.nextInt(5)));

    public static CartLineItem aRandomPhoneCartLineItem() {
        return PHONE_ITEMS.get(rndNumber.nextInt(PHONE_ITEMS.size()));
    }

    public static CartLineItem aLaptopCartLineItem() {
        return new CartLineItem(UUIDGenerator.generate(), ProductFixture.aLaptop(), rndNumber.nextInt(5));
    }
}
