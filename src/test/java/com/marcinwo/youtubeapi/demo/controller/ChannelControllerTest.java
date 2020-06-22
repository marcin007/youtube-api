package com.marcinwo.youtubeapi.demo.controller;

import com.marcinwo.youtubeapi.demo.JsonUtils;
import com.marcinwo.youtubeapi.demo.dto.ChannelDTO;
import com.marcinwo.youtubeapi.demo.entity.*;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
                new Channel("Testoviron", "Kompendium wiedzy o polakach", new User("Stanislaw", "Testo", "testoviron", "1234",
                        Set.of(new Channel()), Set.of(new UserWatchedFilm()), Set.of(new Reply())), Set.of(new Film())),
                new Channel("Kononowicz", "Kompendium wiedzy o Bialymstoku", new User(), Set.of(new Film())),
                new Channel("Biedron", "Kompendium wiedzy o biedronkach", new User(), Set.of(new Film()))
        );
        List<ChannelDTO> channelDTOS = List.of(
                new ChannelDTO(1L, "TestovironDTO", "Kompendium wiedzy o polakach DTO", "Radosław Toczek DTO"),
                new ChannelDTO(1L, "KononowiczDTO", "Kompendium wiedzy o Bialymstoku DTO", "Aleksander Krysiuk DTO"),
                new ChannelDTO(1L, "BiedronDTO", "Kompendium wiedzy o biedronkach DTO", "Agata Doda DTO")
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

    @Test
    public void getChannelByIdTest() throws Exception {

        Channel channel = new Channel("Biedron", "Kompendium wiedzy o biedronkach", new User(), Set.of(new Film()));
        channel.setId(1L);
        ChannelDTO channelDTO = new ChannelDTO(1L, "BiedronDTO", "Kompendium wiedzy o biedronkach DTO", "Agata Doda DTO");

        when(channelService.getChannelById(channel.getId())).thenReturn(channel);
        when(channelMapper.toChannelDTO(channel)).thenReturn(channelDTO);

        mockMvc.perform(get("/channels/1"))
                .andDo(print())
                .andExpect(content().json(JsonUtils.toJsonString(channelDTO)));



    }
}
