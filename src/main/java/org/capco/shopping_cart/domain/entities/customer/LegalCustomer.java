package org.capco.shopping_cart.domain.entities.customer;

import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class LegalCustomer implements Customer {

    private static final String SIREN_ID_REGEX = "[0-9]{14}";
    private static final String VAT_NUMBER_REGEX= "[0-9]{9}";

    private final String id;

    // SIREN
    private final String companyName;
    private final String VATNumber;
    private final String siren;


    public LegalCustomer(String id, String companyName, String siren, String VATNumber) {
        if(!siren.matches(SIREN_ID_REGEX)){
            throw new IllegalArgumentException("SIREN must be a 14 digit number");
        }
        if(!Strings.isBlank(VATNumber) && !VATNumber.matches(VAT_NUMBER_REGEX)){
            throw new IllegalArgumentException("VAT number must be a 9 digit number");
        }
        if(Strings.isBlank(companyName)){
            throw new IllegalArgumentException("Legal form is empty");
        }
        this.id = id;
        this.companyName = companyName;
        this.VATNumber = VATNumber;
        this.siren = siren;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getSiren() {
        return siren;
    }

    public String getVATNumber() {
        return VATNumber;
    }

    @Override
    public CustomerType getCustomerType() {
        return CustomerType.LEGAL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LegalCustomer that = (LegalCustomer) o;
        return Objects.equals(VATNumber, that.VATNumber) && Objects.equals(siren, that.siren);
    }

    @Override
    public int hashCode() {
        return Objects.hash(VATNumber, siren);
    }
}
