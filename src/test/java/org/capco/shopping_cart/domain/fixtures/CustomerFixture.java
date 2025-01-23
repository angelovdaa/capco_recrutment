package org.capco.shopping_cart.domain.fixtures;

import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.customer.LegalCustomer;
import org.capco.shopping_cart.domain.entities.customer.NaturalCustomer;
import org.capco.shopping_cart.domain.entities.id.UUIDGenerator;

import java.util.List;
import java.util.Random;

public class CustomerFixture {

    public static Customer aNaturalCustomer() {
        final String id = UUIDGenerator.generate();
        final String name = "John";
        final String surname = "Doe";
        return new NaturalCustomer(id, name, surname);
    }

    public static Customer aLegalCustomer() {
        final String id = UUIDGenerator.generate();
        final String siren = "00000000000001";
        final String VATNumber = "123456789";
        final String companyName = " CAPCO LTD";
        return new LegalCustomer(id, companyName, siren, VATNumber);
    }

    public static Customer random() {
        List<Customer> customerList = List.of(CustomerFixture.aNaturalCustomer(), CustomerFixture.aLegalCustomer());
        Random random = new Random();
        return customerList.get(random.nextInt(customerList.size()));

    }

}
