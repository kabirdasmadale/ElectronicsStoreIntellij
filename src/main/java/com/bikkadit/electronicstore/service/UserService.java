package com.bikkadit.electronicstore.service;

import com.bikkadit.electronicstore.dtos.PageableResponse;
import com.bikkadit.electronicstore.dtos.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    UserDto getUserById(String userId);

    PageableResponse<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir);

    UserDto updateUser(UserDto userDto,String userId);

    void deleteUser(String userId);

    List<UserDto> searchUser(String keyword);

    UserDto getuserbyEmail(String email);
}
