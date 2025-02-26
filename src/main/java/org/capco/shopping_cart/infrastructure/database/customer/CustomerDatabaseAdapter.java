package org.capco.shopping_cart.infrastructure.database.customer;

import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.entities.customer.CustomerType;
import org.capco.shopping_cart.domain.entities.customer.LegalCustomer;
import org.capco.shopping_cart.domain.entities.customer.NaturalCustomer;
import org.capco.shopping_cart.domain.use_cases.customer.CustomerPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerDatabaseAdapter implements CustomerPort {

    private final CustomerJPARepository customerJPARepository;

    public CustomerDatabaseAdapter(CustomerJPARepository customerJPARepository) {
        this.customerJPARepository = customerJPARepository;
    }

    @Override
    public List<Customer> getByNameContainsIgnoreCase(String name) {
        List<CustomerJPA> customerJPAs = customerJPARepository.findByLastNameContainsIgnoreCaseOrCompanyNameContainsIgnoreCase(name, name);
        return customerJPAs.stream().map(this::toCustomer).collect(Collectors.toList());

    }

    @Override
    public Optional<Customer> getBySiren(String siren) {
        List<CustomerJPA> customerJPAs = customerJPARepository.findBySiren(siren);
        if (customerJPAs.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(toCustomer(customerJPAs.get(0)));

    }

    @Override
    public void save(Customer customer) {
        customerJPARepository.save(fromCustomer(customer));
    }

    private Customer toCustomer(CustomerJPA customerJPA) {
        if (CustomerType.LEGAL.equals(customerJPA.getCustomerType())) {
            return new LegalCustomer(customerJPA.getId(), customerJPA.getCompanyName(), customerJPA.getSiren(), customerJPA.getVATNumber());
        } else if (CustomerType.NATURAL.equals(customerJPA.getCustomerType())) {
            return new NaturalCustomer(customerJPA.getId(), customerJPA.getFirstName(), customerJPA.getLastName());
        } else throw new IllegalArgumentException("Unknown CustomerJpa type");
    }

    private CustomerJPA fromCustomer(Customer customer) {
        if (CustomerType.LEGAL.equals(customer.getCustomerType())) {
            LegalCustomer legalCustomer = (LegalCustomer) customer;
            CustomerJPA customerJPA = new CustomerJPA();
            customerJPA.setId(legalCustomer.getId());
            customerJPA.setSiren(legalCustomer.getSiren());
            customerJPA.setVATNumber(legalCustomer.getVATNumber());
            customerJPA.setCompanyName(legalCustomer.getCompanyName());
            return customerJPA;
        } else if (CustomerType.NATURAL.equals(customer.getCustomerType())) {
            NaturalCustomer naturalCustomer = (NaturalCustomer) customer;
            CustomerJPA customerJPA = new CustomerJPA();
            customerJPA.setId(naturalCustomer.getId());
            customerJPA.setFirstName(naturalCustomer.getFirstName());
            customerJPA.setLastName(naturalCustomer.getLastName());
            return customerJPA;
        } else throw new IllegalArgumentException("Unknown customer type");

    }
}
