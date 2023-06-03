package de.golderosDR.services;

import de.golderosDR.dtos.NewUserDTO;
import de.golderosDR.dtos.UserDTO;
import de.golderosDR.dtos.UserNamesDTO;
import de.golderosDR.mappers.Mapper;
import de.golderosDR.repositories.UsersRepository;
import de.golderosDR.validators.UserNamesDTOValidator;

import java.util.List;

public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean contains(UserDTO userDTO) {
        return usersRepository.contains(userDTO);
    }

    @Override
    public boolean add(NewUserDTO newUserDTO) {
        if (!contains(Mapper.toUserDTO(newUserDTO))) {
            usersRepository.add(newUserDTO);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(UserDTO userDTO) {
        if (contains(userDTO)) {
        usersRepository.remove(userDTO);
        return true;
        }
       return false;
    }

    @Override
    public boolean update(UserDTO target, UserDTO replacement) {
        if (!contains(replacement) && !target.equals(replacement)) {
            usersRepository.update(target, replacement);
            return true;
        }
        return false;
    }

    @Override
    public List<UserDTO> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public UserNamesDTO getAllNames() {
        UserNamesDTO userNamesDTO = usersRepository.getAllNames();
        if (UserNamesDTOValidator.validate(userNamesDTO) && usersRepository.findAll().size() > 0) {
            return userNamesDTO;
        }else
            return null;
    }

    @Override
    public String getLastNameOfOldest() {
        return usersRepository.getOldest().getLastName();
    }

    @Override
    public double getAverageUserAge() {
        return usersRepository.getAverageUsersAge();
    }

    @Override
    public int getAgeOfHighest() {
        return usersRepository.getHighest().getAge();
    }

    @Override
    public String getFullNameOfLowest() {
        UserDTO lowestUser = usersRepository.getLowest();
        return lowestUser.getFirstName() + " " + lowestUser.getLastName();
    }

    @Override
    public List<UserDTO> findByName(UserNamesDTO userNamesDTO) {
        return usersRepository.findByName(userNamesDTO);
    }
}
