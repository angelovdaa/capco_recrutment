package org.capco.shopping_cart.infrastructure.database.customer;

import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.customer.LegalCustomer;
import org.capco.shopping_cart.domain.entities.customer.NaturalCustomer;
import org.capco.shopping_cart.domain.fixtures.CustomerFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class CustomerDatabaseAdapterTest {

    @InjectMocks
    CustomerDatabaseAdapter customerDatabaseAdapter;

    @Mock
    CustomerJPARepository customerJPARepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void saveLegalCustomer_shouldSaveByUsingJpaRepository() {

        // Given
        LegalCustomer customer = (LegalCustomer) CustomerFixture.aLegalCustomer();
        ArgumentCaptor<CustomerJPA> customerJPAArgumentCaptor = ArgumentCaptor.forClass(CustomerJPA.class);

        // When
        customerDatabaseAdapter.save(customer);

        // Then
        verify(customerJPARepository).save(customerJPAArgumentCaptor.capture());
        assertThat(customerJPAArgumentCaptor.getValue()).isEqualToComparingOnlyGivenFields(customer, "id", "siren", "companyName", "VATNumber");
    }

    @Test
    void saveNaturalCustomer_shouldSaveByUsingJpaRepository() {

        // Given
        NaturalCustomer customer = (NaturalCustomer) CustomerFixture.aNaturalCustomer();
        ArgumentCaptor<CustomerJPA> customerJPAArgumentCaptor = ArgumentCaptor.forClass(CustomerJPA.class);

        // When
        customerDatabaseAdapter.save(customer);

        // Then
        verify(customerJPARepository).save(customerJPAArgumentCaptor.capture());
        assertThat(customerJPAArgumentCaptor.getValue()).isEqualToComparingOnlyGivenFields(customer, "id", "firstName", "lastName");
    }

    @Test
    void find_shouldReturnCustomerByCompanyName() {
        // Given

        List<CustomerJPA> expected = List.of(testEntityManager.find(CustomerJPA.class, "939c0a28-c407-4ce3-b661-d96a412a3d29"));


        // When
        doReturn(expected).when(customerJPARepository).findByLastNameContainsIgnoreCaseOrCompanyNameContainsIgnoreCase("capc", "capc");

        // Then
        List<Customer> actual = customerDatabaseAdapter.getByNameContainsIgnoreCase("capc");
        assertThat(actual.size()).isEqualTo(1);
        assertThat(actual.get(0)).isEqualToComparingOnlyGivenFields(expected.get(0), "id", "VATNumber", "companyName", "siren");
    }

    @Test
    void find_shouldReturnCustomerByName() {
        // Given
        List<CustomerJPA> expected = List.of(testEntityManager.find(CustomerJPA.class, "5e18367a-1eb3-4b91-b87a-44cd210ef7ba"));
        // When
        doReturn(expected).when(customerJPARepository).findByLastNameContainsIgnoreCaseOrCompanyNameContainsIgnoreCase("angel", "angel");

        // Then
        List<Customer> actual = customerDatabaseAdapter.getByNameContainsIgnoreCase("angel");
        assertThat(actual.size()).isEqualTo(1);
        assertThat(actual.get(0)).isEqualToComparingOnlyGivenFields(expected.get(0), "id", "firstName", "lastName");

    }

    @Test
    void find_shouldReturnCustomerBySiren() {
        // Given
        String siren = "00000000000004";
        List<CustomerJPA> expected = List.of(testEntityManager.find(CustomerJPA.class, "e37a04a6-273e-4a7f-8715-01cc9b0661a3"));

        // When
        doReturn(expected).when(customerJPARepository).findBySiren(siren);

        // Then
        Optional<Customer> actual = customerDatabaseAdapter.getBySiren(siren);
        Assertions.assertTrue(actual.isPresent());
        assertThat(actual.get()).isEqualToComparingOnlyGivenFields(expected.get(0), "id", "VATNumber", "companyName", "siren");

    }
}
