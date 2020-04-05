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
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserDTO> getUser(){
        return userMapper.toUserDTO(userService.getUser());
    }

    @GetMapping("/{id_user}")
    public UserDTO getUserById(@PathVariable("id_user") Long id){
        return userMapper.toUserDTO(userService.findUserById(id));
    }

    @PostMapping
    public User postUser(@RequestBody User user){
        return userService.postUser(user);
    }

    @DeleteMapping("/{id}")
    public ApiInformation deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ApiInformation("Deleted user", HttpStatus.OK.value());
    }

    @PatchMapping("/{id}")
    public User updateUserById(@PathVariable Long id, @Valid @RequestBody PatchUserDTO userDTO){
        return userService.updateUserById(id, userDTO);
    }

}
