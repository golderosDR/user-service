package de.golderosDR.repositories;

import de.golderosDR.dtos.UserDTO;
import de.golderosDR.dtos.UserNamesDTO;
import de.golderosDR.mappers.Mapper;
import de.golderosDR.models.User;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
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
            throw new RuntimeException(e);
        }
    }
    @Override
    public void add(UserDTO userDTO) {
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.newLine();
            bufferedWriter.write(Mapper.toLine(userDTO));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void remove(UserDTO userDTO) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder
                        .append(line.replace(Mapper.toLine(userDTO) + System.lineSeparator(), ""))
                        .append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(UserDTO target, UserDTO replacement) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder
                        .append(line.replace(Mapper.toLine(target), Mapper.toLine(replacement)))
                        .append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
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
                if (names.length == 2) {
                    for (User user : users) {
                        if (user.getFirstName().equals(names[0]) && user.getLastName().equals(names[1])) {
                            resultList.add(user);
                        }
                    }
                } else  if (names.length == 1){
                    for (User user : users) {
                        if (user.getFirstName().equals(names[0]) || user.getLastName().equals(names[0])) {
                            resultList.add(user);
                        }
                    }
                }
        return Mapper.toUserDTOList(resultList);
    }
}
