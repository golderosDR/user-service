package de.golderosDR.validators;

import de.golderosDR.dtos.UserNamesDTO;

import static de.golderosDR.messages.ErrorMessages.INVALID_INPUT_VALUE_ERROR;

public  abstract class UserNamesDTOValidator {

    public static boolean validate(UserNamesDTO userNamesDTO) {
        String[] names = userNamesDTO.getNames();
        if (names == null || names.length < 1) {
            throw new RuntimeException(INVALID_INPUT_VALUE_ERROR);
        }
        return true;
    }
}
