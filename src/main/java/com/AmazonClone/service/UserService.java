package com.AmazonClone.service;
import com.AmazonClone.payload.UserDto;
import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(int userId);
    List<UserDto> getAllUsers();
    void deleteUser(int userId);
    UserDto updateUser(int userId,UserDto userDto);
}
