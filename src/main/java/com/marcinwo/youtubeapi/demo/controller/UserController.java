package com.marcinwo.youtubeapi.demo.controller;

import com.marcinwo.youtubeapi.demo.ApiInformation;
import com.marcinwo.youtubeapi.demo.dto.PatchUserDTO;
import com.marcinwo.youtubeapi.demo.dto.UserDTO;
import com.marcinwo.youtubeapi.demo.entity.User;
import com.marcinwo.youtubeapi.demo.mapper.UserMapper;
import com.marcinwo.youtubeapi.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/users")
    public List<UserDTO> getUser(){
        return userMapper.toUserDTO(userService.findAll());
    }

    @GetMapping("/users/{user_id}")
    public UserDTO getUserById(@PathVariable("user_id") Long id){
        return userMapper.toUserDTO(userService.findUserById(id));
    }

    @PostMapping("/users")
    public UserDTO postUser(@RequestBody UserDTO user){
        return userMapper.toUserDTO(userService.saveUser(userMapper.toUserEntity(user)));
    }

    @DeleteMapping("/users/{id}")
    public ApiInformation deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ApiInformation("Deleted user", HttpStatus.OK.value());
    }

    @PatchMapping("/users/{id}")
    public User updateUserById(@PathVariable Long id, @Valid @RequestBody PatchUserDTO userDTO) {
        return userService.updateUserById(id, userDTO);
    }


}
