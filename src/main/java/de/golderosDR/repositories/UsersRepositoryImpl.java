package de.golderosDR.repositories;

import de.golderosDR.dtos.NewUserDTO;
import de.golderosDR.dtos.UserDTO;
import de.golderosDR.dtos.UserNamesDTO;
import de.golderosDR.mappers.Mapper;
import de.golderosDR.models.User;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static de.golderosDR.messages.ErrorMessages.FILE_NOT_FOUND_ERROR;

public class UsersRepositoryImpl implements UsersRepository {
    private final String fileName;

    public UsersRepositoryImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<UserDTO> findAll() {
        return Mapper.toUserDTOList(getAll());
    }

    private List<User> getAll() {
        try {
            return Mapper.toUserList(Files.readAllLines(new File(fileName).toPath()));
        } catch (IOException e) {
            throw new RuntimeException(FILE_NOT_FOUND_ERROR);
        }
    }

    @Override
    public void add(NewUserDTO newUserDTO) {
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(System.lineSeparator() + Mapper.toLine(newUserDTO));
        } catch (IOException e) {
            throw new RuntimeException(FILE_NOT_FOUND_ERROR);
        }
    }

    @Override
    public void remove(UserDTO userDTO) {
        List<User> users = getAll();
        users.removeIf(user -> userDTO.equals(Mapper.toUserDTO(user)));
        StringBuilder output = new StringBuilder();
        for (User user : users) {
            output.append(Mapper.toLine(user)).append(System.lineSeparator());
        }
        output.deleteCharAt(output.length() - 1);

        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(output.toString());
        } catch (IOException e) {
            throw new RuntimeException(FILE_NOT_FOUND_ERROR);
        }
    }

    @Override
    public void update(UserDTO target, UserDTO replacement) {
        List<User> users = getAll();
        StringBuilder output = new StringBuilder();
        for (User user : users) {
            if (target.equals(Mapper.toUserDTO(user))) {
                user = new User(
                        replacement.getFirstName(),
                        replacement.getLastName(),
                        replacement.getAge(),
                        replacement.getHeight(),
                        user.getAddress());
            }
            output.append(Mapper.toLine(user)).append(System.lineSeparator());
        }
        output.deleteCharAt(output.length() - 1);

        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(output.toString());
        } catch (IOException e) {
            throw new RuntimeException(FILE_NOT_FOUND_ERROR);
        }
    }

    @Override
    public UserDTO getLowest() {
        List<User> users = getAll();
        int indexOfLowest = 0;
        for (int i = 1; i < users.size(); i++) {
            if (users.get(i).getHeight() < users.get(indexOfLowest).getHeight()) {
                indexOfLowest = i;
            }
        }
        return Mapper.toUserDTO(users.get(indexOfLowest));
    }

    @Override
    public UserDTO getHighest() {
        List<User> users = getAll();
        int indexOfHighest = 0;
        for (int i = 1; i < users.size(); i++) {
            if (users.get(i).getHeight() > users.get(indexOfHighest).getHeight()) {
                indexOfHighest = i;
            }
        }
        return Mapper.toUserDTO(users.get(indexOfHighest));
    }

    @Override
    public boolean contains(UserDTO userDTO) {
        List<User> users = getAll();
        return Mapper.toUserDTOList(users).contains(userDTO);
    }
    @Override
    public double getAverageUsersAge() {
        List<User> users = getAll();
        double averageAge,
                ageSum = 0;
        for (User user : users) {
            ageSum = ageSum + user.getAge();
        }
        averageAge = ageSum / users.size();
        return averageAge;
    }

    @Override
    public UserDTO getOldest() {
        List<User> users = getAll();
        int indexOfOldest = 0;
        for (int i = 1; i < users.size(); i++) {
            if (users.get(i).getAge() > users.get(indexOfOldest).getAge()) {
                indexOfOldest = i;
            }
        }
        return Mapper.toUserDTO(users.get(indexOfOldest));
    }

    @Override
    public UserNamesDTO getAllNames() {
        List<User> users = getAll();
        String[] names = new String[users.size()];
        for (int i = 0; i < users.size(); i++) {
            names[i] = users.get(i).getFirstName();
        }
        return new UserNamesDTO(names);
    }

    @Override
    public List<UserDTO> findByName(UserNamesDTO namesDTO) {
        List<User> users = getAll();
        List<User> resultList = new ArrayList<>();
        String[] names = namesDTO.getNames();
        switch (names.length) {
            case 2 -> {
                for (User user : users) {
                    if (user.getFirstName().equals(names[0]) && user.getLastName().equals(names[1])) {
                        resultList.add(user);
                    }
                }
            }
            case 1 -> {
                for (User user : users) {
                    if (user.getFirstName().equals(names[0]) || user.getLastName().equals(names[0])) {
                        resultList.add(user);
                    }
                }
            }
        }
        return Mapper.toUserDTOList(resultList);
    }
}
