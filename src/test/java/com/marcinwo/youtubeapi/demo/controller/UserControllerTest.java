package com.marcinwo.youtubeapi.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marcinwo.youtubeapi.demo.ExampleData;
import com.marcinwo.youtubeapi.demo.JsonUtils;
import com.marcinwo.youtubeapi.demo.dto.UserDTO;
import com.marcinwo.youtubeapi.demo.entity.Channel;
import com.marcinwo.youtubeapi.demo.entity.Reply;
import com.marcinwo.youtubeapi.demo.entity.User;
import com.marcinwo.youtubeapi.demo.entity.UserWatchedFilm;
import com.marcinwo.youtubeapi.demo.mapper.UserMapper;
import com.marcinwo.youtubeapi.demo.service.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Test
    public void getUsersTest() throws Exception {
        //given
        List<User> users = List.of(
                new User("Jarek", "Doda", "DODIX", "1234", Set.of(new Channel()), Set.of(new UserWatchedFilm()), Set.of(new Reply())),
                new User("Jarek2", "Doda2", "DODIX2", "12345", Set.of(new Channel()), Set.of(new UserWatchedFilm()), Set.of(new Reply())),
                new User("Jarek3", "Doda3", "DODIX3", "12346", Set.of(new Channel()), Set.of(new UserWatchedFilm()), Set.of(new Reply()))
        );
        List<UserDTO> userDTOS = List.of(
                new UserDTO(1L, "JarunioDTO", "DodiDTO", "DIDI", "1234"),
                new UserDTO(2L, "JarunioDTO", "DodiDTO", "DIDI", "1234"),
                new UserDTO(3L, "JarunioDTO", "DodiDTO", "DIDI", "1234")
        );

        //when
        when(userService.findAll()).thenReturn(users);
        when(userMapper.toUserDTO(anyCollection())).thenReturn(userDTOS);

        //then
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(content().string(CoreMatchers.containsString("Jar")))
                .andExpect(content().json(JsonUtils.toJsonString(userDTOS)));
    }

    @Test
    public void postUserTest() throws Exception { // TODO err
        //given
        UserDTO userDTOBeforeSave = new UserDTO(1L, "231", "23", "edwr", "32");
        UserDTO userDTOAfterSave = new UserDTO(1L, "231", "23", "edwr", "32");


        //when
        when(userMapper.toUserEntity(userDTOBeforeSave)).thenReturn(ExampleData.user());
        when(userService.postUser(ExampleData.user())).thenReturn(ExampleData.user());
        when(userMapper.toUserDTO(ExampleData.user())).thenReturn(userDTOAfterSave);

        //then
        mockMvc.perform(post("/users")
                .content(JsonUtils.toJsonString(userDTOBeforeSave))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("user1"))
        .andExpect(content().json(JsonUtils.toJsonString(userDTOAfterSave)));
    }

}
