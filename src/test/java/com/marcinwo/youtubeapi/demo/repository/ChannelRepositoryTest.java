package com.marcinwo.youtubeapi.demo.repository;


import com.marcinwo.youtubeapi.demo.ExampleData;
import com.marcinwo.youtubeapi.demo.entity.Channel;
import com.marcinwo.youtubeapi.demo.entity.User;
import com.marcinwo.youtubeapi.demo.exeption.ChannelNotFoundException;
import com.marcinwo.youtubeapi.demo.exeption.UserNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

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

    @Test
    public void given_userExistAndChannelsExist_when_findAllByUserId_then_returnChannels() { //ok
        User myUser = ExampleData.user();

        Channel myChannel1 = ExampleData.channel1();
        Channel myChannel2 = new Channel("aaa1", "ooo1", myUser, new HashSet<>());
        Channel myChannel3 = new Channel("aaa1", "ooo1", myUser, new HashSet<>());

        testEntityManager.persistAndFlush(myChannel1);
        testEntityManager.persistAndFlush(myChannel2);
        testEntityManager.persistAndFlush(myChannel3);

        List<Channel> channels = channelRepository.findAllByUser_Id(1L);

        assertEquals(3, channels.size());
        assertEquals("aaa1", channels.get(0).getName());


//        assertEquals(channels.get(0).getUser().getId(), myChannel.getId());
//        assertEquals(channels.get(0).getName(), myChannel.getName());
    }

    @Test()
    public void given_userHasNoChannels_when_findAllByUserId_then_returnEmptyChannelList(){}




}
