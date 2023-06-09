package com.bikkadit.electronicstore.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="user_table")
public class User {

    @Id
    private String userId;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_email",unique = true)
    private String userEmail;

    @Column(name="user_password",length = 10)
    private String userPassword;

    @Column(name="user_gender")
    private String gender;

    @Column(name="user_about")
    private String about;

    @Column(name="image_name")
    private String imageName;


}
