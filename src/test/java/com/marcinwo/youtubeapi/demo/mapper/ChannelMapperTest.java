package com.marcinwo.youtubeapi.demo.mapper;

import com.marcinwo.youtubeapi.demo.dto.ChannelDTO;
import com.marcinwo.youtubeapi.demo.entity.Channel;
import com.marcinwo.youtubeapi.demo.entity.Film;
import com.marcinwo.youtubeapi.demo.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class ChannelMapperTest {

    private ChannelMapper channelMapper = new ChannelMapperImpl();

    @Test
    public void toChannelDtoTest(){
        Channel channel = new Channel("komedia", "seriale", new User(), Set.of(new Film()));

        ChannelDTO channelDTO = channelMapper.toChannelDTO(channel);

        assertEquals(channelDTO.getName(), channel.getName());
        assertEquals(channelDTO.getDescription(), channel.getDescription());
        assertEquals(channelDTO.getId(), channel.getId());
        assertEquals(channelDTO.getUser(), channel.getUser()); //todo czy tak się to powinno robić?

    }
}
