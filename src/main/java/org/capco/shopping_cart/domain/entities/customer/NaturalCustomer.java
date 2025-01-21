package org.capco.shopping_cart.domain.entities.customer;

import java.util.Objects;

public class NaturalCustomer implements Customer {

    private final String id;
    private final String firstName;
    private final String lastName;

    public NaturalCustomer(String id, String firstName, String lastName) {
        if(lastName.isBlank()){
            throw new IllegalArgumentException("Customer name is empty");
        }
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public CustomerType getCustomerType() {
        return CustomerType.NATURAL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NaturalCustomer that = (NaturalCustomer) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
