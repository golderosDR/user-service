package de.golderosDR.dtos;

import java.util.Objects;

public class UserDTO {
    private String firstName;
    private String lastName;
    private int age;
    private double height;
    public UserDTO(String firstName, String lastName, int age, double height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
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

    public String toString() {
        return String.format("%s %s, age %d, height %.2f",
                firstName,
                lastName,
                age,
                height
        );
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO userDTO)) return false;
        return age == userDTO.age
                && Double.compare(userDTO.height, height) == 0
                && firstName.equals(userDTO.firstName)
                && lastName.equals(userDTO.lastName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, height);
    }
}
