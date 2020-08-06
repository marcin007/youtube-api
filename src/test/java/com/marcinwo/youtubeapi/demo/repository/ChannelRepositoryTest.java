package com.marcinwo.youtubeapi.demo.repository;


import com.marcinwo.youtubeapi.demo.ExampleData;
import com.marcinwo.youtubeapi.demo.entity.Channel;
import com.marcinwo.youtubeapi.demo.entity.User;
import com.marcinwo.youtubeapi.demo.exeption.ChannelNotFoundException;
import com.marcinwo.youtubeapi.demo.exeption.UserNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.util.CollectionUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

@ActiveProfiles("test")
@DataJpaTest
@RunWith(SpringRunner.class)
public class ChannelRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ChannelRepository channelRepository;

    @Before
    public void beforeEach() {
        channelRepository.deleteAll();
    }

    @Test//ok
    public void given_userHasChannels_when_findAllByUserId_then_returnChannels() {
        User myUser = ExampleData.user();

        Channel myChannel1 = new Channel("aaa1", "ooo1", myUser, new HashSet<>());
        Channel myChannel2 = new Channel("aaa2", "ooo2", myUser, new HashSet<>());
        Channel myChannel3 = new Channel("aaa3", "ooo3", myUser, new HashSet<>());

        testEntityManager.persistAndFlush(myChannel1);
        testEntityManager.persistAndFlush(myChannel2);
        testEntityManager.persistAndFlush(myChannel3);

        List<Channel> channels = channelRepository.findAllByUser_Id(1L);

        assertEquals(3, channels.size());
        assertEquals("aaa1", channels.get(0).getName());
        assertEquals("aaa2", channels.get(1).getName());
        assertEquals("aaa3", channels.get(2).getName());
        assertEquals("ooo1", channels.get(0).getDescription());
        assertEquals("ooo2", channels.get(1).getDescription());
        assertEquals("ooo3", channels.get(2).getDescription());

    }

    @Test()//ok
    public void given_userHasNoChannels_when_findAllByUserId_then_returnEmptyChannelList(){
        List<Channel> channels = channelRepository.findAllByUser_Id(1L);

        assertThat(channels).isEmpty();
    }




}
