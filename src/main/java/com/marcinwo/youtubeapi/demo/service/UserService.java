package com.marcinwo.youtubeapi.demo.service;


import com.marcinwo.youtubeapi.demo.dto.PatchUserDTO;
import com.marcinwo.youtubeapi.demo.dto.UserDTO;
import com.marcinwo.youtubeapi.demo.entity.User;
import com.marcinwo.youtubeapi.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User postUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public User findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cant find user by id."));
    }

    public User updateUserById(Long id, PatchUserDTO patchUserDTO){
        User user = findUserById(id);
        user.setPassword(patchUserDTO.getPassword());
        user.setFirstName(patchUserDTO.getFirstName());
        user.setLastName(patchUserDTO.getLastName());
        return userRepository.save(user);
    }


}
