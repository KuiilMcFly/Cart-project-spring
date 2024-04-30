package com.carmine.UserCart.Service;

import com.carmine.UserCart.Dto.UserRequestDto;
import com.carmine.UserCart.Models.User;
import com.carmine.UserCart.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public  UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserRequestDto> getUsertDtoById(UserRequestDto userRequestDto){
        List<User> users = userRepository.findByLastName(userRequestDto.getSurname());
        List<UserRequestDto> usersList = new ArrayList<>();
        for (User user:users){
            UserRequestDto newUserRequestDto = new UserRequestDto(user.getName(), user.getSurname());
            usersList.add(newUserRequestDto);
        }
        return usersList;
    }
}
