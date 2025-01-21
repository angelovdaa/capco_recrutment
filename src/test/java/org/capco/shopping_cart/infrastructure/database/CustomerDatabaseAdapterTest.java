package org.capco.shopping_cart.infrastructure.database;

import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.customer.LegalCustomer;
import org.capco.shopping_cart.domain.entities.customer.NaturalCustomer;
import org.capco.shopping_cart.domain.fixtures.CustomerFixture;
import org.capco.shopping_cart.infrastructure.database.customer.CustomerDatabaseAdapter;
import org.capco.shopping_cart.infrastructure.database.customer.CustomerJPA;
import org.capco.shopping_cart.infrastructure.database.customer.CustomerJPARepository;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

    void find_shouldReturnCustomerByNameOrCompanyName() {

    }

    void find_shouldReturnCustomerBySiren(){

    }




}
