package com.marcinwo.youtubeapi.demo.service;

import com.marcinwo.youtubeapi.demo.ExampleData;
import com.marcinwo.youtubeapi.demo.dto.ChannelDTO;
import com.marcinwo.youtubeapi.demo.dto.PatchChannelDTO;
import com.marcinwo.youtubeapi.demo.entity.Channel;
import com.marcinwo.youtubeapi.demo.entity.User;
import com.marcinwo.youtubeapi.demo.exeption.ChannelNotFoundException;
import com.marcinwo.youtubeapi.demo.repository.ChannelRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChannelServiceTest {

    @Autowired
    private ChannelService channelService;

    @MockBean
    private ChannelRepository channelRepository;


    @Test
    public void given_repositoryHasTwoChannels_when_getChannels_then_returnChannelList() {

        List<Channel> channels = List.of(
                new Channel("channel1", "channel desc", new User(), new HashSet<>()),
                new Channel("channel2", "channel desc", new User(), new HashSet<>()));

        when(channelRepository.findAll()).thenReturn(channels);

        List<Channel> serviceChannels = channelService.getChannels();

        assertThat(serviceChannels).isEqualTo(channels);
        verify(channelRepository, times(1)).findAll();
        verifyNoMoreInteractions(channelRepository);
    }

    @Test
    public void given_repositoryHasNoChannels_when_getChannels_then_returnEmptyList() {

        List<Channel> serviceChannels = channelService.getChannels();
        assertThat(serviceChannels).isEmpty();
    }

    @Test
    public void given_repositoryHasChannel_when_getChannelById_then_returnChannel() {

        Channel channel = new Channel("channel1", "channel desc", new User(), new HashSet<>());
        channel.setId(1L);

        when(channelRepository.findById(1L)).thenReturn(Optional.of(channel));

        Channel serviceChannel = channelService.getChannelById(channel.getId());

        assertThat(serviceChannel).isEqualTo(channel);
    }

    @Test(expected = ChannelNotFoundException.class)
    public void given_repositoryHasNoChannel_when_getChannelById_then_channelNotFound() {

        when(channelRepository.findById(1L)).thenThrow(new ChannelNotFoundException("Channel not found."));
        channelService.getChannelById(1L);

    }

    @Test
    public void given_userHasChannels_when_getUserChannelsById_then_returnChannelList() {
        User user = new User("Adam", "Kowalski", "akowalski", "pass", new HashSet<>(), new HashSet<>(), new HashSet<>());
        user.setId(1L);

        List<Channel> channels = List.of(
                new Channel("channel1", "channel desc", new User(), new HashSet<>()),
                new Channel("channel2", "channel desc", new User(), new HashSet<>()));


        when(channelRepository.findAllByUser_Id(1L)).thenReturn(channels);

        List<Channel> channelsByUserId = channelService.getChannelsByUserId(1L);

        assertThat(channelsByUserId).isEqualTo(channels);
    }

    @Test
    public void given_userHasNoChannels_when_getUserChannelsById_then_returnEmptyChannelList(){

        List<Channel> channelsByUserId = channelService.getChannelsByUserId(1L);

        assertThat(channelsByUserId).isEmpty();
    }

    @Test
    public void given_channelExist_when_save_then_channelIsSaved(){

        Channel channel = new Channel("channel1", "channel desc", new User(), new HashSet<>());

        when(channelRepository.save(channel)).thenReturn(channel);

        Channel channel1 = channelService.save(channel);

        assertThat(channel1).isNotNull();
        verify(channelRepository).save(any(Channel.class));
    }

    @Test
    public void given_repositoryHasChannelWithThatName_when_save_then_throwError(){
        Channel channel = new Channel("channel1", "channel desc", new User(), new HashSet<>());

        when(channelRepository.save(channel)).thenThrow(new Error("Channel is exist"));

    }

    @Test(expected = ChannelNotFoundException.class)
    public void given_repositoryHasChannel_when_deleteChannelById_then_channelNotExist(){
        Channel channel = new Channel("channel1", "channel desc", new User(), new HashSet<>());
        channel.setId(1L);

        when(channelRepository.findById(1L))
                .thenReturn(Optional.of(channel))
                .thenThrow(new ChannelNotFoundException("Can not find this channel"));

        Channel channelBeforeDeletion = channelService.getChannelById(1L);
        assertNotNull(channelBeforeDeletion);

        channelService.deleteChannelById(1L);
        channelService.getChannelById(1L);
    }

    @Test
    public void given_channelExist_when_updateChannelById_then_descriptionUpdated(){
        User user = new User("Adam", "Kowalski", "akowalski", "pass", new HashSet<>(), new HashSet<>(), new HashSet<>());
        user.setId(1L);

        Channel channel = new Channel("channel1", "channel desc", user, new HashSet<>());
        channel.setId(1L);
        channel.setDescription("channel desc");
        PatchChannelDTO dto = new PatchChannelDTO("channel desc updated");

        when(channelRepository.findById(1L)).thenReturn(Optional.of(channel));
        when(channelRepository.save(channel)).thenReturn(channel);

        channelService.updateChannelById(1L, dto);

        assertThat(channel.getDescription()).isEqualTo("channel desc updated");
    }

    @Test
    public void given_channelExist_when_updateChannelById_then_onlyDescriptionUpdated(){
        Channel channel = mock(Channel.class);

        PatchChannelDTO dto = new PatchChannelDTO("channel desc updated");

        when(channelRepository.findById(1L)).thenReturn(Optional.of(channel));
        when(channelRepository.save(channel)).thenReturn(channel);

        channelService.updateChannelById(1L, dto);

        verify(channelRepository, times(1)).findById(1L);
        verify(channelRepository, times(1)).save(channel);

        verify(channel, times(1)).setDescription("channel desc updated");

    }



}



