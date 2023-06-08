package com.bikkadit.electronicstore.service;

import com.bikkadit.electronicstore.dtos.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    UserDto getUserById(String userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto,String userId);

    void deleteUser(String userId);

    List<UserDto> searchUser(String keyword);

    UserDto getuserbyEmail(String email);
}
