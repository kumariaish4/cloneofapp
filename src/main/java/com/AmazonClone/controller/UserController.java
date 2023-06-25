package com.AmazonClone.controller;

import com.AmazonClone.payload.UserDto;
import com.AmazonClone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    public UserService userService;


    @PostMapping
    public ResponseEntity<UserDto>createUser(@RequestBody UserDto userDto){
        UserDto dto = userService.createUser(userDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserDto>getAllUser(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto>getUserById(@PathVariable(name="id") int id){
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto>updateUser(@PathVariable(name="id") int id,@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.updateUser(id,userDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name="id") int id){
        userService.deleteUser(id);
    }
}
