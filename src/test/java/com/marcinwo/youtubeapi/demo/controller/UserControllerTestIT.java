package com.marcinwo.youtubeapi.demo.controller;

import com.marcinwo.youtubeapi.demo.JsonUtils;
import com.marcinwo.youtubeapi.demo.YoutubeApiApplication;
import com.marcinwo.youtubeapi.demo.dto.PatchUserDTO;
import com.marcinwo.youtubeapi.demo.mapper.UserMapper;
import com.marcinwo.youtubeapi.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = YoutubeApiApplication.class)
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@SpringBootTest
public class UserControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    @Test
    public void given_UserNotExist_when_PatchUser_then222_NotFound222() throws Exception {
        userService.deleteUserById(22L);

        PatchUserDTO patchUserDTO = new PatchUserDTO("ala", "ala", "1234");

        mockMvc.perform(patch("/users/22")
                .content(JsonUtils.toJsonString(patchUserDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))

                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("User not found."));

    }
}
