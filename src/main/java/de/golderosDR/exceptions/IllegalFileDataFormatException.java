package de.golderosDR.exceptions;

public class IllegalFileDataFormatException extends RuntimeException{
    public static String ILLEGAL_FORMAT_OR_DAMAGED_FILE = "Файл содержит элементы неподдерживаемого формата или поврежден.";
    public IllegalFileDataFormatException(String message) {
        super(message);
    }

}
