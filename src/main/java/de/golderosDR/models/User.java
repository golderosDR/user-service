package de.golderosDR.models;

import java.util.Objects;

public class User {
    private String firstName;
    private String lastName;
    private int age;
    private double height;
    private Address address;

    public User(String firstName, String lastName, int age, double height, String street, String houseNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.address = new Address(street,houseNumber);
    }
    public User(String firstName, String lastName, int age, double height, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return age == user.age
                && Double.compare(user.height, height) == 0
                && firstName.equals(user.firstName)
                && lastName.equals(user.lastName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, height);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", address=" + address +
                '}';
    }
}
