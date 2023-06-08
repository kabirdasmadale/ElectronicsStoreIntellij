package com.bikkadit.electronicstore.service.impl;

import com.bikkadit.electronicstore.dtos.UserDto;
import com.bikkadit.electronicstore.exception.ResourceNotFoundException;
import com.bikkadit.electronicstore.helper.AppConstant;
import com.bikkadit.electronicstore.model.User;
import com.bikkadit.electronicstore.repositary.UserRepositary;
import com.bikkadit.electronicstore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepositary userRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
        log.info("Request Starting  to save the user");
        User user = this.mapper.map(userDto, User.class);
        //for every time random Id will be stored
        String randomId = UUID.randomUUID().toString();
        user.setUserId((randomId));
        user.setImageName("kabir.png");
        User user1= this.userRepo.save(user);
        log.info("Request completed  to save the user");
        return this.mapper.map(user1, UserDto.class);
    }


    @Override
    public UserDto getUserById(String userId) {
        log.info("Request Starting  to get the user by userId : {}", userId);
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.EXCEPTION_MSG));
        log.info("Request completed to  get the user by userId : {}", userId);
        return this.mapper.map(user, UserDto.class);

    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Request Starting  to get All users");
        List<User> list = this.userRepo.findAll();
        List<UserDto> userdata = list.stream().map(data -> this.mapper.map(data, UserDto.class)).collect(Collectors.toList());
        log.info("Request completed  to get All users");
        return userdata;
    }

    @Override
    public UserDto updateUser(UserDto userDto,String userId) {
        log.info("Request Starting  to update the user by userId : {}", userId);
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.EXCEPTION_MSG));
        user.setUserName(userDto.getUserName());
        user.setGender(userDto.getGender());
        user.setAbout(userDto.getAbout());
        user.setUserPassword(userDto.getUserPassword());
        user.setImageName(userDto.getImageName());
        User user1 = this.userRepo.save(user);
        log.info("Request completed  to update the user by userId : {}", userId);
        return this.mapper.map(user1, UserDto.class);

    }



    @Override
    public void deleteUser(String userId) {
        log.info("Request Starting  to delete the user by userId : {}", userId);
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.EXCEPTION_MSG));
        this.userRepo.delete(user);
        log.info("Request completed  to delete the user by userId : {}", userId);

    }


    @Override
    public UserDto getuserbyEmail(String email) {
        log.info("Request Starting  to get the user by email ;{}", email);
        User user = this.userRepo.findByUserEmail(email).orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_EMAIL));
        log.info("Request completed  to get the user by email ;{}", email);
        return this.mapper.map(user, UserDto.class);
    }


    @Override
    public List<UserDto> searchUser(String keyword) {
        log.info("Request Starting  to search the user with keyword :{}", keyword);
        List<User> list = this.userRepo.findByUserNameContaining(keyword);
        log.info("Request completed to search the user with keyword :{}", keyword);
        return list.stream().map(data -> this.mapper.map(data, UserDto.class)).collect(Collectors.toList());
    }


}