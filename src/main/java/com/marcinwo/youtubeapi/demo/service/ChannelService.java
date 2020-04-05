package com.marcinwo.youtubeapi.demo.service;


import com.marcinwo.youtubeapi.demo.entity.Channel;
import com.marcinwo.youtubeapi.demo.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChannelService {

    private ChannelRepository channelRepository;

    @Autowired
    public ChannelService(ChannelRepository channelRepository){
        this.channelRepository = channelRepository;
    }


    public List<Channel> getChannels(){
        return channelRepository.findAll();
    }

    public Channel getChannelById(Long id){
        return channelRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Can not find this channel"));
    }

    public List<Channel> getUserChannelsById(Long id){
        return channelRepository.findAllByUser_Id(id);
    }

    public Channel postUserChannelById(Long id, Channel channel){
        channelRepository.postUserChannelById(id,channel);
    }
}
