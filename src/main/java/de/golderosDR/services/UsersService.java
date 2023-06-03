package de.golderosDR.services;
import de.golderosDR.dtos.NewUserDTO;
import de.golderosDR.dtos.UserDTO;
import de.golderosDR.dtos.UserNamesDTO;

import java.util.List;

public interface UsersService {
    boolean contains(UserDTO userDTO);
    boolean add(NewUserDTO newUserDTO);
    boolean remove(UserDTO userDTO);
    boolean update(UserDTO target, UserDTO replacement);
    List<UserDTO> findAll();
    UserNamesDTO getAllNames();
    String getLastNameOfOldest();
    double getAverageUserAge();
    int getAgeOfHighest();
    String getFullNameOfLowest();
    List<UserDTO> findByName(UserNamesDTO userNamesDTO);
}
