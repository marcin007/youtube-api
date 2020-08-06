package com.marcinwo.youtubeapi.demo.controller;

import com.marcinwo.youtubeapi.demo.JsonUtils;
import com.marcinwo.youtubeapi.demo.dto.ChannelDTO;
import com.marcinwo.youtubeapi.demo.entity.*;
import com.marcinwo.youtubeapi.demo.exeption.ChannelNotFoundException;
import com.marcinwo.youtubeapi.demo.exeption.UserNotFoundException;
import com.marcinwo.youtubeapi.demo.mapper.ChannelMapper;
import com.marcinwo.youtubeapi.demo.service.ChannelService;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ChannelController.class)
public class ChannelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChannelService channelService;

    @MockBean
    private ChannelMapper channelMapper;

    @Test
    public void getChannelsTest() throws Exception {
        //given
        List<Channel> channels = List.of(
                new Channel("Testoviron", "Channel 1 desc", new User("Stanislaw", "Testo", "testoviron", "1234",
                        Set.of(new Channel()), Set.of(new UserWatchedFilm()), Set.of(new Reply())), Set.of(new Film())),
                new Channel("Kononowicz", "Channel 2 desc", new User(), Set.of(new Film())),
                new Channel("Biedron", "Channel 3 desc", new User(), Set.of(new Film()))
        );
        List<ChannelDTO> channelDTOS = List.of(
                new ChannelDTO(1L, "TestovironDTO", "Channel 1 desc DTO", "Radosław Toczek DTO"),
                new ChannelDTO(1L, "KononowiczDTO", "Channel 2 desc DTO", "Aleksander Krysiuk DTO"),
                new ChannelDTO(1L, "BiedronDTO", "Channel 3 desc DTO", "Agata Doda DTO")
        );

        //when
        when(channelService.getChannels()).thenReturn(channels);
        when(channelMapper.toChannelDTO(anyCollection())).thenReturn(channelDTOS);

        //then
        mockMvc.perform(get("/channels"))
                .andDo(print())
                .andExpect(content().string(CoreMatchers.containsString("Toczek")))
                .andExpect(content().json(JsonUtils.toJsonString(channelDTOS)));
    }

    @Test //todo ok??
    public void getChannelByIdTest() throws Exception {

        Channel channel = new Channel("Biedron", "Channel 1 desc", new User(), Set.of(new Film()));
        channel.setId(1L);
        ChannelDTO channelDTO = new ChannelDTO(1L, "BiedronDTO", "Channel 1 desc DTO", "Agata Doda DTO");

        when(channelService.getChannelById(channel.getId())).thenReturn(channel);
        when(channelMapper.toChannelDTO(channel)).thenReturn(channelDTO);

        mockMvc.perform(get("/channels/1"))
                .andDo(print())
                .andExpect(content().json(JsonUtils.toJsonString(channelDTO)));
    }

    @Test//todo ok ????
    public void given_UserExist_when_getChannelsByUserId_then_returnChannelList() throws Exception {
        User user = new User("Stanislaw", "Testo", "testoviron", "1234");
        user.setId(1L);
        List<Channel> channels = List.of(
                new Channel("Testoviron", "Channel 1 desc", user, Set.of(new Film())),
                new Channel("Kononowicz", "Channel 2 desc", user, Set.of(new Film())),
                new Channel("Biedron", "Channel 3 desc", user, Set.of(new Film()))
        );
        List<ChannelDTO> channelDTOS = List.of(
                new ChannelDTO(user.getId(), "TestovironDTO", "Channel 1 desc DTO", "Radosław Toczek DTO"),
                new ChannelDTO(user.getId(), "KononowiczDTO", "Channel 2desc DTO", "Aleksander Krysiuk DTO"),
                new ChannelDTO(user.getId(), "BiedronDTO", "Channel 3 d2sc DTO", "Agata Doda DTO")
        );

        when(channelService.getChannelsByUserId(user.getId())).thenReturn(channels);
        when(channelMapper.toChannelDTO(channels)).thenReturn(channelDTOS);

        mockMvc.perform(get("/users/1/channels"))
                .andDo(print())
                .andExpect(content().json(JsonUtils.toJsonString(channelDTOS)));
    }

    @Test //todo  ok ???????
    public void given_UserNotExist_when_getChannelsByUserId_then_NotFound() throws Exception {

        when(channelService.getChannelsByUserId(22L)).thenThrow(new UserNotFoundException("User not found."));

        mockMvc.perform(get("/users/22/channels"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("User not found."));
    }

    // todo DELETE ????



}
