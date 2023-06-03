package de.golderosDR.validators;

import de.golderosDR.dtos.NewUserDTO;
import de.golderosDR.exceptions.IllegalUserFieldArgumentException;

import static de.golderosDR.exceptions.IllegalUserFieldArgumentException.*;
import static de.golderosDR.exceptions.IllegalUserFieldArgumentException.WRONG_ADDRESS_SIZE_MESSAGE;

public abstract class NewUserDTOValidator{
    public static boolean validate(NewUserDTO newUserDTO) {

        if (newUserDTO.getFirstName().length() < 1
                || newUserDTO.getLastName().length() < 1) {
            throw new IllegalUserFieldArgumentException(WRONG_NAME_SIZE_MESSAGE);
        }
        if (newUserDTO.getAge() < 0) {
            throw new IllegalUserFieldArgumentException(WRONG_AGE_VALUE_MESSAGE);
        }
        if (newUserDTO.getHeight() < 0) {
            throw new IllegalUserFieldArgumentException(WRONG_HEIGHT_VALUE_MESSAGE);
        }
        if (newUserDTO.getAddress().getHouseNumber().length() < 1
                || newUserDTO.getAddress().getStreet().length() < 1) {
            throw new IllegalUserFieldArgumentException(WRONG_ADDRESS_SIZE_MESSAGE);
        }
        return true;
    }
}
