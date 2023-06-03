package de.golderosDR.dtos;

import de.golderosDR.models.Address;


public class NewUserDTO {
    private String firstName;
    private String lastName;
    private int age;
    private double height;
    private Address address;
    public NewUserDTO(String firstName, String lastName, int age, double height, String street, String houseNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.address = new Address(street,houseNumber);
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public Address getAddress() { return address;}
}


