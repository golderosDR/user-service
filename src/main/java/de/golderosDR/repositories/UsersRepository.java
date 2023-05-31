package de.golderosDR.repositories;

import de.golderosDR.dtos.UserDTO;
import de.golderosDR.dtos.UserNamesDTO;

import java.util.List;

public interface UsersRepository {
    List<UserDTO> findAll();
    void add(UserDTO userDTO);
    void remove(UserDTO userDTO);
    void update(UserDTO target, UserDTO replacement);
    UserDTO getLowest();
    UserDTO getHighest();
    double getAverageUsersAge();
    UserDTO getOldest();
    UserNamesDTO getAllNames();
    List<UserDTO> findByName(UserNamesDTO namesDTO);




}
