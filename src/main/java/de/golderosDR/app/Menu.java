package de.golderosDR.app;

import de.golderosDR.dtos.NewUserDTO;
import de.golderosDR.dtos.UserDTO;
import de.golderosDR.dtos.UserNamesDTO;
import de.golderosDR.entry.ManualDataEntry;
import de.golderosDR.mappers.Mapper;
import de.golderosDR.repositories.UsersRepositoryImpl;
import de.golderosDR.services.UsersService;
import de.golderosDR.services.UsersServiceImpl;
import de.golderosDR.validators.CommandValidator;

import java.util.List;
import java.util.Scanner;
public class Menu {
    private final UsersService usersService;
    public final static String CANCEL_OPERATION = "Операция отменена.";
    public final static String WRONG_COMMAND = "Команда не распознана.";
    Menu() {
        this.usersService = new UsersServiceImpl(new UsersRepositoryImpl("users.txt"));
    }
    public void showMainMenu() {
        System.out.println(MAIN_MENU_TEXT);
    }
    public void menuCancel() {
        System.out.println(CANCEL_OPERATION);
    }
    public void wrongCommand() {
        System.out.println(WRONG_COMMAND);
    }
    public void printAllUsers() {
        System.out.println("Пользователи:");
        printUserDTOList(usersService.findAll());
    }
    public void printUsersByInputtedName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя, фамилию или полное имя пользователя для поиска:");
        UserNamesDTO namesDTO = Mapper.toUserNameDTO(scanner.nextLine().strip());
        List<UserDTO> userDTOList = usersService.findByName(namesDTO);
        if (userDTOList.size() > 0) {
            System.out.println("Найденные пользователи:");
            printUserDTOList(userDTOList);
        } else {
            System.out.println("Пользователей с такими данными не найдено.");
        }
    }
    public void save() {
        NewUserDTO newUserDTO = ManualDataEntry.getNewUserDTO();
        if (usersService.add(newUserDTO)) {
            System.out.println("Новый пользователь успешно добавлен.");
        } else {
            System.out.println("Такой пользователь уже существует! Пользователь не добавлен.");
        }
    }
    public void remove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(REMOVE_SUBMENU_TEXT);
        switch (scanner.next()) {
            case "1" -> {
                System.out.println("Пользователи:");
                List<UserDTO> userDTOList = usersService.findAll();
                printUserDTOList(userDTOList);
                System.out.println("Введите номер пользователя из таблицы для удаления:");
                String temp = scanner.next();
                if (CommandValidator.validate(temp) && (Integer.parseInt(temp) <= userDTOList.size())) {
                    if (usersService.remove(userDTOList.get(Integer.parseInt(temp) - 1))) {
                        System.out.println("Пользователь успешно удален.");
                    }
                } else {
                    wrongCommand();
                }
            }
            case "2" -> {
                UserDTO userDTO = ManualDataEntry.getUserDTO();
                if (usersService.remove(userDTO)) {
                    System.out.println("Пользователь успешно удален.");
                } else {
                    System.out.println("Пользователь с такими данными отсутствует.");
                }

            }
            case "0" -> menuCancel();
            default -> wrongCommand();
        }
    }
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Пользователи:");
        List<UserDTO> userDTOList = usersService.findAll();
        printUserDTOList(userDTOList);
        System.out.println("Введите номер пользователя из таблицы для удаления:");
        String temp = scanner.next();
        if (CommandValidator.validate(temp) && (Integer.parseInt(temp) <= userDTOList.size())) {
            int count = Integer.parseInt(temp) - 1;
            System.out.println(CHANGE_DATA_SUBMENU_TEXT);

            switch (scanner.next()) {
                case "1" -> updateAllUserData(userDTOList.get(count));
                case "2" -> {
                    System.out.println(CHOOSE_UR_DESTINY_SUBMENU_TEXT);
                    updateEntryUserData(userDTOList.get(count));
                }
                case "0" -> menuCancel();
                default -> wrongCommand();
            }
        } else {
            wrongCommand();
        }
    }
    private void updateAllUserData(UserDTO target) {
        UserDTO replacement = ManualDataEntry.getUserDTO();
        if (usersService.update(target, replacement)) {
            System.out.println("Данные успешно обновлены.");
        } else {
            System.out.println("Данные пользователей совпадают. Данные не были изменены.");
        }
    }
    private void updateEntryUserData(UserDTO target) {
        Scanner scanner = new Scanner(System.in);
        UserDTO replacement = null;
        switch (scanner.next()) {
            case "1" -> {
                String newFirstName = ManualDataEntry.getFirstName();
                replacement = new UserDTO(newFirstName, target.getLastName(), target.getAge(), target.getHeight());
            }
            case "2" -> {
                String newLastName = ManualDataEntry.getLastName();
                replacement = new UserDTO(target.getFirstName(), newLastName, target.getAge(), target.getHeight());
            }
            case "3" -> {
                int newAge = ManualDataEntry.getAge();
                replacement = new UserDTO(target.getFirstName(), target.getLastName(), newAge, target.getHeight());
            }
            case "4" -> {
                double newHeight = ManualDataEntry.getHeight();
                replacement = new UserDTO(target.getFirstName(), target.getLastName(), target.getAge(), newHeight);
            }
            case "0" -> menuCancel();
            default -> wrongCommand();
        }
        if (usersService.update(target, replacement)) {
            System.out.println("Данные успешно обновлены.");
        } else {
            System.out.println("Данные пользователей совпадают. Данные не были изменены.");
        }
    }
    public void utilSubMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(UTILITY_SUBMENU_TEXT);
        String temp = scanner.next();

        while (!temp.equals("0")) {
            switch (temp) {
                case "1" -> {
                    String[] userNames = usersService.getAllNames().getNames();
                    System.out.println("Имена всех пользователей:");
                    for (String name : userNames) {
                        System.out.println(name);
                    }
                }
                case "2" -> System.out.printf("Фамилия самого взрослого пользователя: %s.%n%n",
                        usersService.getLastNameOfOldest());

                case "3" -> System.out.printf("Средний возраст всех пользователей: %.1f.%n%n",
                        usersService.getAverageUserAge());

                case "4" -> System.out.printf("Возраст самого высокого пользователя: %d.%n%n",
                        usersService.getAgeOfHighest());

                case "5" -> System.out.printf("Имя и фамилия самого низкого пользователя: %s.%n%n",
                        usersService.getFullNameOfLowest());

                default -> wrongCommand();
            }
            System.out.println(UTILITY_SUBMENU_TEXT);
            temp = scanner.next();
        }
    }
    private void printUserDTOList(List<UserDTO> userDTOList) {
        int counter = 1;
        for (UserDTO userDTO : userDTOList) {
            System.out.printf("%d. %s%n", counter++, userDTO);
        }
    }
    private final static String MAIN_MENU_TEXT = """
            Введите необходимый пункт меню для выполнения:
            1. Вывести всех пользователей.
            2. Получить данные пользователя по имени/фамилии.
            3. Сохранить нового пользователя.
            4. Изменить данные пользователя.
            5. Удалить пользователя.
            6. Вспомогательные функции.
            0. Выход.""";
    private final static String REMOVE_SUBMENU_TEXT = """
            Алгоритм удаления пользователя:
            1. Удалить пользователя из списка пользователей по номеру.
            2. Удалить пользователя по введенным данным.
            0. Отмена.""";
    private final static String CHANGE_DATA_SUBMENU_TEXT = """
            Алгоритм изменения данных пользователя:
            1. Изменить все данные пользователя.
            2. Изменить отдельный параметр.
            0. Отмена.""";
    private final static String CHOOSE_UR_DESTINY_SUBMENU_TEXT = """
            Какие данные пользователя вы бы хотели изменить?
            1. Имя.
            2. Фамилию.
            3. Возраст.
            4. Рост.
            0. Отмена.""";
    private final static String UTILITY_SUBMENU_TEXT = """
            Введите необходимый пункт меню для выполнения:
            1. Вывести имена всех пользователей.
            2. Вывести фамилию самого взрослого пользователя.
            3. Вывести средний возраст всех пользователей.
            4. Вывести возраст самого высокого пользователя.
            5. Вывести имя и фамилию самого низкого пользователя.
            0. Назад в главное меню.""";
}
