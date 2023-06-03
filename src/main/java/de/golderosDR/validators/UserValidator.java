package de.golderosDR.validators;

import de.golderosDR.exceptions.IllegalUserFieldArgumentException;
import de.golderosDR.models.User;

import static de.golderosDR.exceptions.IllegalUserFieldArgumentException.*;

public abstract class UserValidator{

    public static boolean validate(User user) {
        if (user.getFirstName().length() < 1
                || user.getLastName().length() < 1) {
            throw new IllegalUserFieldArgumentException(WRONG_NAME_SIZE_MESSAGE);
        }
        if (user.getAge() < 0) {
            throw new IllegalUserFieldArgumentException(WRONG_AGE_VALUE_MESSAGE);
        }
        if (user.getHeight() < 0) {
            throw new IllegalUserFieldArgumentException(WRONG_HEIGHT_VALUE_MESSAGE);
        }
        if (user.getAddress().getHouseNumber().length() < 1
                || user.getAddress().getStreet().length() < 1) {
            throw new IllegalUserFieldArgumentException(WRONG_ADDRESS_SIZE_MESSAGE);
        }
        return true;
    }

}
