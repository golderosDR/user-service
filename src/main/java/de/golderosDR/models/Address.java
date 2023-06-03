package de.golderosDR.models;

import java.util.Objects;

public class Address {
    private String street;
    private String houseNumber;

    public Address(String street, String houseNumber) {
        this.street = street;
        this.houseNumber = houseNumber;
    }
    public String getStreet() {
        return street;
    }
    public String getHouseNumber() {
        return houseNumber;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(street, address.street) && Objects.equals(houseNumber, address.houseNumber);
    }
    @Override
    public int hashCode() {
        return Objects.hash(street, houseNumber);
    }
    @Override
    public String toString() {
        return street + " " + houseNumber;
    }
}
