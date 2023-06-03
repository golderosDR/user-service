package de.golderosDR.mappers;

import de.golderosDR.dtos.NewUserDTO;
import de.golderosDR.dtos.UserDTO;
import de.golderosDR.dtos.UserNamesDTO;
import de.golderosDR.exceptions.IllegalFileDataFormatException;
import de.golderosDR.messages.ErrorMessages;
import de.golderosDR.models.User;
import de.golderosDR.validators.UserValidator;

import java.util.ArrayList;
import java.util.List;

public abstract class Mapper {
    public static User toUser(String line) {
        try {
            String[] parsed = line.split("\\|");
            String firstName = parsed[0];
            String lastName = parsed[1];
            int age = Integer.parseInt(parsed[2]);
            double height = Double.parseDouble(parsed[3]);
            String street = parsed[4];
            String houseNumber = parsed[5];

            User user = new User(firstName, lastName, age, height, street, houseNumber);
            if (UserValidator.validate(user)) {
                return user;
            } else return null;

        } catch (RuntimeException e) {
            throw new IllegalFileDataFormatException(IllegalFileDataFormatException.ILLEGAL_FORMAT_OR_DAMAGED_FILE);
        }
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getAge(),
                user.getHeight()
        );
    }

    public static UserDTO toUserDTO(NewUserDTO newUserDTO) {
        return new UserDTO(
                newUserDTO.getFirstName(),
                newUserDTO.getLastName(),
                newUserDTO.getAge(),
                newUserDTO.getHeight()
        );
    }

    public static List<User> toUserList(List<String> lines) {
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(Mapper.toUser(line));
        }
        return users;
    }

    public static UserNamesDTO toUserNameDTO(String line) {
        try {
            return new UserNamesDTO(line.split(" "));
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(ErrorMessages.WRONG_DATA_FORMAT_ERROR);
        }
    }

    public static List<UserDTO> toUserDTOList(List<User> userList) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(Mapper.toUserDTO(user));
        }
        return userDTOList;
    }

    public static String toLine(NewUserDTO newUserDTO) {
        return String.format("%s|%s|%d|%.2f|%s|%s",
                        newUserDTO.getFirstName(),
                        newUserDTO.getLastName(),
                        newUserDTO.getAge(),
                        newUserDTO.getHeight(),
                        newUserDTO.getAddress().getStreet(),
                        newUserDTO.getAddress().getHouseNumber()
                )
                .replaceFirst(",", ".");
    }

    public static String toLine(User user) {
        return String.format("%s|%s|%d|%.2f|%s|%s",
                        user.getFirstName(),
                        user.getLastName(),
                        user.getAge(),
                        user.getHeight(),
                        user.getAddress().getStreet(),
                        user.getAddress().getHouseNumber()
                )
                .replaceFirst(",", ".");
    }
}
