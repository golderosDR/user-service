package de.golderosDR.validators;

import de.golderosDR.dtos.UserDTO;
import de.golderosDR.exceptions.IllegalUserFieldArgumentException;

import static de.golderosDR.exceptions.IllegalUserFieldArgumentException.*;

public abstract class UserDTOValidator{

    public static boolean validate(UserDTO userDTO) {
        if (userDTO.getFirstName().length() < 1
                || userDTO.getLastName().length() < 1) {
            throw new IllegalUserFieldArgumentException(WRONG_NAME_SIZE_MESSAGE);
        }
        if (userDTO.getAge() < 0) {
            throw new IllegalUserFieldArgumentException(WRONG_AGE_VALUE_MESSAGE);
        }
        if (userDTO.getHeight() < 0) {
            throw new IllegalUserFieldArgumentException(WRONG_HEIGHT_VALUE_MESSAGE);
        }
        return true;
    }
}
