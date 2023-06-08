package com.bikkadit.electronicstore.dtos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String gender;
    private String about;
    private String imageName;

}
