package com.AmazonClone.payload;

import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String password;
}
