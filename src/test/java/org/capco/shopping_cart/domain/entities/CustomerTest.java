package org.capco.shopping_cart.domain.entities;

import org.assertj.core.util.Strings;
import org.capco.shopping_cart.domain.entities.customer.LegalCustomer;
import org.capco.shopping_cart.domain.entities.customer.NaturalCustomer;
import org.capco.shopping_cart.domain.entities.id.UUIDGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerTest {

    @Test
    public void testValidNaturalCustomer() {
        // Given
        NaturalCustomer customer = new NaturalCustomer(UUIDGenerator.generate(),"Dimitar", "Angelov");
        // Then
        assertThat(customer.getFirstName()).isEqualTo("Dimitar");
        assertThat(customer.getLastName()).isEqualTo("Angelov");
    }

    @Test
    public void testInvalidNaturalCustomer() {
        assertThrows(IllegalArgumentException.class, () ->  new NaturalCustomer(UUIDGenerator.generate(),"Dimitar", ""));
    }

    @Test
    public void testValidLegalCustomer() {
        // Given
        LegalCustomer customer = new LegalCustomer(UUIDGenerator.generate(),"Capco LTD.", "12345678912345", "123456789");

        assertThat(customer.getCompanyName()).isEqualTo("Capco LTD.");
        assertThat(customer.getVATNumber()).isEqualTo("123456789");
        assertThat(customer.getSiren()).isEqualTo("12345678912345");
    }

    @Test
    public void testValidLegalCustomer_NoVATNumber() {
        // Given
        LegalCustomer customer = new LegalCustomer(UUIDGenerator.generate(),"Capco LTD.", "12345678912345", null);
        // Then
        assertThat(customer.getCompanyName()).isEqualTo("Capco LTD.");
        assertThat(customer.getSiren()).isEqualTo("12345678912345");
        assertTrue(Strings.isNullOrEmpty(customer.getVATNumber()));
    }

    @Test
    public void testInvalidLegalCustomer_NoName() {
        // Given
        String companyName = null;
        // Then
        assertThrows(IllegalArgumentException.class, () ->  new LegalCustomer(UUIDGenerator.generate(),companyName, "12345678912345", "123456789"));
    }


}
