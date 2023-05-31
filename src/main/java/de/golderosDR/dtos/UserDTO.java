package de.golderosDR.dtos;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String toString() {
        return String.format("%s %s, age %d, height %.2f",
                firstName,
                lastName,
                age,
                height
        );
    }
}
