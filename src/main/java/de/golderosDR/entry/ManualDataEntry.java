package de.golderosDR.entry;

import de.golderosDR.dtos.NewUserDTO;
import de.golderosDR.dtos.UserDTO;
import de.golderosDR.validators.NewUserDTOValidator;
import de.golderosDR.validators.UserDTOValidator;

import java.util.Scanner;

public abstract class ManualDataEntry {
    public static NewUserDTO getNewUserDTO() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные нового пользователя:");
        System.out.println("Введите имя: ");
        String firstName = scanner.nextLine();
        System.out.println("Введите фамилию: ");
        String lastName = scanner.nextLine();
        System.out.println("Введите возраст:");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите рост:");
        double height = Double.parseDouble(scanner.nextLine().replace(",", "."));
        System.out.println("Введите название улицы:");
        String street = scanner.nextLine();
        System.out.println("Введите номер дома:");
        String houseNumber = scanner.nextLine();
        NewUserDTO newUserDTO = new NewUserDTO(firstName, lastName, age, height, street, houseNumber);
        if (NewUserDTOValidator.validate(newUserDTO)) {
            return newUserDTO;
        } else
            return null;
    }

    public static UserDTO getUserDTO() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные пользователя:");
        System.out.println("Введите имя: ");
        String firstName = scanner.nextLine();
        System.out.println("Введите фамилию: ");
        String lastName = scanner.nextLine();
        System.out.println("Введите возраст:");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите рост:");
        double height = Double.parseDouble(scanner.nextLine().replace(",", "."));
        UserDTO userDTO = new UserDTO(firstName, lastName, age, height);
        if (UserDTOValidator.validate(userDTO)) {
            return userDTO;
        } else
            return null;
    }

    public static String getFirstName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите новое имя пользователя");
        return scanner.next();
    }
    public static String getLastName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите новую фамилию пользователя");
        return scanner.next();
    }
    public static int getAge() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите новый возраст пользователя");
        return Integer.parseInt(scanner.next());
    }
    public static double getHeight() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите новый рост пользователя");
        return Double.parseDouble(scanner.next().replace(",", "."));
    }
}
