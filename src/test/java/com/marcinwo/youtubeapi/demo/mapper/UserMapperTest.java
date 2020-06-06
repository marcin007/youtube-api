package com.marcinwo.youtubeapi.demo.mapper;

import com.marcinwo.youtubeapi.demo.ExampleData;
import com.marcinwo.youtubeapi.demo.dto.UserDTO;
import com.marcinwo.youtubeapi.demo.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


public class UserMapperTest {

    private UserMapper  userMapper = new UserMapperImpl();

    @Test
    public void toUserDtoTest(){
        User user = ExampleData.user();

        UserDTO userDTO = userMapper.toUserDTO(user);

        assertEquals(userDTO.getFirstName(), user.getFirstName());
        assertEquals(userDTO.getLastName(), user.getLastName());
        assertEquals(userDTO.getUserName(), user.getUserName());
        assertEquals(userDTO.getPassword(), user.getPassword());
        assertEquals(userDTO.getId(), user.getId());
    }

    @Test
    public void toUserEntityTest(){
        User user = ExampleData.user();

        UserDTO userDTO = userMapper.toUserDTO(user);

        assertEquals(user.getPassword(), userDTO.getPassword());
        assertEquals(user.getFirstName(), userDTO.getFirstName());
        assertEquals(user.getLastName(), userDTO.getLastName());
        assertEquals(user.getUserName(), userDTO.getUserName());
        assertEquals(user.getId(), userDTO.getId());
    }

}
