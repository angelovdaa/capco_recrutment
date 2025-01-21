package org.capco.shopping_cart.domain.entities.id;

import java.util.UUID;

public class UUIDGenerator {
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
