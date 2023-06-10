package com.bikkadit.electronicstore.controller;

import com.bikkadit.electronicstore.dtos.ImageResponse;
import com.bikkadit.electronicstore.dtos.PageableResponse;
import com.bikkadit.electronicstore.dtos.UserDto;
import com.bikkadit.electronicstore.helper.ApiResponse;
import com.bikkadit.electronicstore.helper.AppConstant;
import com.bikkadit.electronicstore.service.FileService;
import com.bikkadit.electronicstore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Value("${user.profile.image.path}")
    private String imageUplodPath;

    /**
     * author Kabirdas Madale
     *
     * @param userDto
     * @return
     * @apiNote this api is used for save user
     */
    @PostMapping("/user")
    public ResponseEntity<UserDto> saveUser( @Valid @RequestBody UserDto userDto) {
        log.info(" Starting request for service layer to save the user");
        UserDto userdto = this.userService.saveUser(userDto);
        log.info("Ending request  for service layer to save the user");
        return new ResponseEntity<>(userdto, HttpStatus.CREATED);
    }

    /**
     * @param userId
     * @return
     * @author Kabirdas Madale
     * @apiNote this api is used to get user by id
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        log.info(" Starting request for service layer to get the user by userId : {}", userId);
        UserDto userdto = this.userService.getUserById(userId);
        log.info("Ending request for service layer to get the user by userId : {}", userId);
        return new ResponseEntity<>(userdto, HttpStatus.FOUND);

    }

    /**
     * @return
     * @author Kabirdas Madale
     * @apiNote this api is used to get all user
     */

    @GetMapping()
    public ResponseEntity<PageableResponse<UserDto>> getAllUsers(
            @RequestParam (value = "pageNumber",defaultValue = "0",required = false)  int pageNumber,
            @RequestParam (value ="pageSize",defaultValue = "6",required = false)  int pageSize,
            @RequestParam (value ="sortBy",defaultValue = "userName",required = false)  String sortBy,
            @RequestParam (value ="sortDir",defaultValue = "asc",required = false) String sortDir
    ) {
        log.info(" Starting request  for service layer to get All users");
        PageableResponse<UserDto> allUsers = this.userService.getAllUsers(pageNumber, pageSize, sortBy, sortDir);
        log.info("Ending request for service layer to get All users");
        return new ResponseEntity<>(allUsers, HttpStatus.OK);

    }

    /**
     * @param userDto
     * @param userId
     * @return
     * @author Kabirdas Madale
     * @apiNote this api is used to update user
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable String userId) {
        log.info(" Starting request for service layer to update the user by userId : {}", userId);
        UserDto userdto = this.userService.updateUser(userDto, userId);
        log.info("Ending request for service layer to update the user by userId : {}", userId);
        return new ResponseEntity<>(userdto, HttpStatus.OK);

    }

    /**
     * @param userId
     * @return
     * @author Kabirdas madale
     * @apiNote this api is used to delete user by id
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
        log.info(" Starting request  for service layer to delete the user by userId : {}", userId);
        this.userService.deleteUser(userId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message(AppConstant.USER_DELETE)
                .success(true)
                .Status(HttpStatus.OK)
                .build();
        log.info("Ending request for service layer to delete the user by userId : {}", userId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    /**
     * @param email
     * @return
     * @author Kabirdas Madale
     * @apiNote this api is used to get user by email
     */

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getuserbyEmail(@PathVariable String email) {
        log.info(" Starting request for service layer to get the user by email ;{}", email);
        UserDto userdto = this.userService.getuserbyEmail(email);
        log.info("Ending request for service layer to get the user by email ;{}", email);
        return new ResponseEntity<>(userdto, HttpStatus.OK);

    }

    /**
     * @param keyword
     * @return
     * @author Kabirdas Madale
     * @apiNote this api is used to serch user
     */
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keyword) {
        log.info(" Starting request  for service layer to search the user with keyword :{}", keyword);
        List<UserDto> userdto = this.userService.searchUser(keyword);
        log.info("ending request for service layer to search the user with keyword :{}", keyword);
        return new ResponseEntity<>(userdto, HttpStatus.FOUND);

    }

    /**
     * @author Kabirdas Madale
     * @apiNote  this api is used to uplod the image
     * @param image
     * @param userId
     * @return
     * @throws IOException
     */
//uplod user image

    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uplodImage(@RequestParam("userImage") MultipartFile image, @PathVariable String userId) throws IOException {
        String imageName = fileService.uplodfiles(image, imageUplodPath);
        UserDto user = userService.getUserById(userId);
        user.setImageName(imageName);
        UserDto userDto = userService.updateUser(user, userId);
        ImageResponse imageResponse=ImageResponse.builder().imageName(imageName).success(true).Status(HttpStatus.CREATED).build();
    return  new ResponseEntity<>(imageResponse,HttpStatus.CREATED );
    }
    // serve image

    /**
     * @author Kabirdas Madale
     * @apiNote  this api is used to dawanlod image
     * @param userId
     */
    @GetMapping("/image/{userId}")
    public void dawanlodeServeImage(@PathVariable String userId){
        UserDto user = userService.getUserById(userId);
        log.info("get user image :{}",user.getImageName());

    }

}

