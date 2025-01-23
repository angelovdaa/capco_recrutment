package org.capco.shopping_cart.infrastructure.database.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerJPARepository extends JpaRepository<CustomerJPA, UUID> {

    List<CustomerJPA> findByLastNameContainsIgnoreCaseOrCompanyNameContainsIgnoreCase(String lastName, String companyName);

    List<CustomerJPA> findBySiren(String siren);


}
