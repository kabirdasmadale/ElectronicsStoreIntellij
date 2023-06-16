package com.bikkadit.electronicstore.dtos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String userId;

    @NotBlank(message = "Invalid User Name: Empty User name")
    @NotNull(message = "Invalid User Name: User Name is NULL")
    @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
    private String userName;

    @Email(message = "Invalid email")
    private String userEmail;

    @NotBlank(message = "Invalid user password: Empty User password")
    @NotNull(message = "Invalid user password: User password is NULL")
    @Size(min = 5, max = 30, message = "Invalid user password: Must be of 5 - 30 characters")
    private String userPassword;

    @NotBlank(message = "Invalid gender: Empty gender")
    @NotNull(message = "Invalid gender: gender is NULL")
    @Size(min = 4, max = 6, message = "Invalid gender: Must be of 4 - 6 characters")
    private String gender;

    @NotBlank(message = "Invalid about: Empty about")
    @NotNull(message = "Invalid about: about is NULL")
    @Size(min = 9, max = 30, message = "Invalid about: Must be of 9 - 30 characters")
    private String about;

    private String imageName;

}
