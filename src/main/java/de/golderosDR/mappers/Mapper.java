package de.golderosDR.mappers;

import de.golderosDR.dtos.UserDTO;
import de.golderosDR.dtos.UserNamesDTO;
import de.golderosDR.exceptions.IllegalFileDataFormatException;
import de.golderosDR.exceptions.IllegalUserFieldArgumentException;
import de.golderosDR.messages.ErrorMessages;
import de.golderosDR.models.User;

import static de.golderosDR.exceptions.IllegalUserFieldArgumentException.WRONG_NAME_SIZE_MESSAGE;
import static de.golderosDR.exceptions.IllegalUserFieldArgumentException.WRONG_AGE_VALUE_MESSAGE;
import static de.golderosDR.exceptions.IllegalUserFieldArgumentException.WRONG_HEIGHT_VALUE_MESSAGE;

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
            if (firstName.length() <1 || lastName.length() < 1 ) {
                throw new IllegalUserFieldArgumentException(WRONG_NAME_SIZE_MESSAGE);
            }
            if (age < 0) {
                throw  new IllegalUserFieldArgumentException(WRONG_AGE_VALUE_MESSAGE);
            }
            if (height < 0) {
                throw  new IllegalUserFieldArgumentException(WRONG_HEIGHT_VALUE_MESSAGE);
            }

            return new User(firstName, lastName, age, height);

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
    public static List<User> toUserList(List<String> lines) {
        List<User> users = new ArrayList<>();
        for (String line: lines) {
           users.add(Mapper.toUser(line));
        }
        return users;
    }
    public static UserDTO toUserDTO(String line) {
        try {
            String[] parsed = line.split(" ");
            String firstName = parsed[0];
            String lastName = parsed[1];
            int age = Integer.parseInt(parsed[2]);
            double height = Double.parseDouble(parsed[3]);

            return new UserDTO(firstName, lastName, age, height);
        } catch (RuntimeException e) {
            throw new RuntimeException(ErrorMessages.WRONG_DATA_FORMAT_ERROR);
        }
    }
    public static UserNamesDTO toUserNameDTO(String line) {
        try {
            return new UserNamesDTO(line.split(" "));
        } catch (RuntimeException e) {
            throw new RuntimeException(ErrorMessages.WRONG_DATA_FORMAT_ERROR);
        }
    }
    public static List<UserDTO> toUserDTOList(List<User> userList) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user: userList) {
            userDTOList.add(Mapper.toUserDTO(user));
        }
        return userDTOList;
    }
    public static String toLine(UserDTO userDTO) {
        return String.format("%s|%s|%d|%.2f",
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getAge(),
                userDTO.getHeight()
                )
                .replaceFirst(",", ".");
    }

}
