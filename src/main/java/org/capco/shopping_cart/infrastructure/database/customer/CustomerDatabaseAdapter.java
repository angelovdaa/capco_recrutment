package org.capco.shopping_cart.infrastructure.database.customer;

import org.capco.shopping_cart.domain.entities.customer.Customer;
import org.capco.shopping_cart.domain.use_cases.customer.CustomerPort;
import org.capco.shopping_cart.domain.entities.customer.CustomerType;
import org.capco.shopping_cart.domain.entities.customer.LegalCustomer;
import org.capco.shopping_cart.domain.entities.customer.NaturalCustomer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerDatabaseAdapter implements CustomerPort {

    private final CustomerJPARepository customerJPARepository;

    public CustomerDatabaseAdapter(CustomerJPARepository customerJPARepository) {
        this.customerJPARepository = customerJPARepository;
    }

    @Override
    public List<Customer> getByName(String name) {
        List<CustomerJPA> customerJPAs = customerJPARepository.findByLastNameContainsIgnoreCaseOrCompanyNameContainsIgnoreCase(name, name);
        return customerJPAs.stream().map(this::toCustomer).collect(Collectors.toList());

    }

    @Override
    public List<LegalCustomer> getBySiren(String siren) {
        return customerJPARepository.findBySiren(siren)
                .stream()
                .map(customerJPA -> (LegalCustomer) toCustomer(customerJPA))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Customer customer) {
        customerJPARepository.save(fromCustomer(customer));
    }

    private Customer toCustomer(CustomerJPA customerJPA) {
        if (CustomerType.LEGAL.equals(customerJPA.getCustomerType())) {
//            LegalCustomerJPA legalCustomerJPA = (LegalCustomerJPA) customerJPA;
            return new LegalCustomer(customerJPA.getId(), customerJPA.getSiren(), customerJPA.getCompanyName(), customerJPA.getVATNumber());
        } else if (CustomerType.NATURAL.equals(customerJPA.getCustomerType())) {
//            NaturalCustomerJPA naturalCustomerJPA = (NaturalCustomerJPA) customerJPA;
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
