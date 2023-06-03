package de.golderosDR.validators;

public abstract class CommandValidator {
    public static boolean validate(String command) {
        return command.matches("[-+]?\\d+");
    }
}
