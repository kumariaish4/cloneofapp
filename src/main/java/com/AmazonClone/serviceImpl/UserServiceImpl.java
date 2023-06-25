package com.AmazonClone.serviceImpl;

import com.AmazonClone.entities.User;
import com.AmazonClone.payload.UserDto;
import com.AmazonClone.repository.UserRepository;
import com.AmazonClone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserRepository userRepository;



    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert DTO to entity
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAddress(userDto.getAddress());
        user.setPhoneNumber(userDto.getPhoneNumber());
        // Set other properties

        // Save the entity
        User savedUser = userRepository.save(user);

        // Convert saved entity to DTO
        UserDto savedUserDTO = new UserDto();
        savedUserDTO.setId(savedUser.getId());
        savedUserDTO.setUsername(savedUser.getUsername());
        savedUserDTO.setEmail(savedUser.getEmail());
        savedUserDTO.setFirstName(savedUser.getFirstName());
        savedUserDTO.setLastName(savedUser.getLastName());
        savedUserDTO.setPassword(savedUser.getPassword());
        savedUserDTO.setAddress(savedUser.getAddress());
        savedUserDTO.setPhoneNumber(savedUser.getPhoneNumber());

        return savedUserDTO;
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        // Convert entity to DTO
        UserDto userDto= new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        userDto.setAddress(user.getAddress());
        userDto.setPhoneNumber(user.getPhoneNumber());
        // Set other properties

        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        // Retrieve all entities
        List<User> users = userRepository.findAll();

        // Convert entities to DTOs
        List<UserDto> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setPassword(user.getPassword());
            userDto.setAddress(user.getAddress());
            userDto.setPhoneNumber(user.getPhoneNumber());
            // Set other properties

            userDTOs.add(userDto);
        }

        return userDTOs;
    }

    @Override
    public void deleteUser(int userId) {
        // Delete the entity by ID
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto updateUser(int userId,UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("id not found"));
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        //user.setPassword(userDto.getPassword());
        user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
        user.setPhoneNumber(userDto.getPhoneNumber());
        User newUser = userRepository.save(user);
        UserDto dto=new UserDto();
        dto.setId(newUser.getId());
        dto.setEmail(newUser.getEmail());
        dto.setFirstName(newUser.getFirstName());
        dto.setLastName(newUser.getLastName());
        dto.setPassword(newUser.getPassword());
        dto.setPhoneNumber(newUser.getPhoneNumber());
        dto.setUsername(newUser.getUsername());
        dto.setAddress(newUser.getAddress());
        return dto;
    }
}

