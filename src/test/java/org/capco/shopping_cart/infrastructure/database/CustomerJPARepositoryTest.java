package org.capco.shopping_cart.infrastructure.database;

import org.assertj.core.api.Assertions;
import org.capco.shopping_cart.domain.entities.customer.CustomerType;
import org.capco.shopping_cart.domain.entities.id.UUIDGenerator;
import org.capco.shopping_cart.infrastructure.database.customer.CustomerJPA;
import org.capco.shopping_cart.infrastructure.database.customer.CustomerJPARepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class CustomerJPARepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    CustomerJPARepository customerJPARepository;

    @Test
    void save_shouldReturnCustomerSaved() {
        // Given a dirty recor (no validation logic in repository)
        CustomerJPA expected = new CustomerJPA();
        expected.setId(UUIDGenerator.generate());
        expected.setFirstName("Doe");
        expected.setLastName("John");
        expected.setCompanyName("Whatever");
        expected.setSiren("Whatever");
        expected.setVATNumber("Whatever");
        expected.setCustomerType(CustomerType.LEGAL);

        // When
        customerJPARepository.save(expected);

        // Then
        CustomerJPA actual = testEntityManager.find(CustomerJPA.class, expected.getId());
        assertThat(expected).isEqualToComparingFieldByField(actual);
    }

    @Test
    void find_shouldReturnCustomerByNameOrCompanyName() {
        // Given
        String expectedID1 ="5e18367a-1eb3-4b91-b87a-44cd210ef7ba";
        String expectedID2 ="939c0a28-c407-4ce3-b661-d96a412a3d29";
        // WHen
        List<CustomerJPA> actual = customerJPARepository.findByLastNameContainsIgnoreCaseOrCompanyNameContainsIgnoreCase("angel", "capc");
        Set<String> actualIds = actual.stream().map(CustomerJPA::getId).collect(Collectors.toSet());
        // Then
        Assertions.assertThat(actualIds).contains(expectedID1, expectedID2);
    }


    @Test
    void find_shouldReturnCustomerBySiren(){
        // Given
        String expectedID ="939c0a28-c407-4ce3-b661-d96a412a3d29";
        // When
        List<CustomerJPA> actual = customerJPARepository.findBySiren("00000000000001");
        // Then
        Set<String> actualIds = actual.stream().map(CustomerJPA::getId).collect(Collectors.toSet());
        Assertions.assertThat(actualIds).contains(expectedID);


    }
}
