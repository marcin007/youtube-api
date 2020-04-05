package com.marcinwo.youtubeapi.demo.controller;

import com.marcinwo.youtubeapi.demo.dto.ChannelDTO;
import com.marcinwo.youtubeapi.demo.entity.Channel;
import com.marcinwo.youtubeapi.demo.mapper.ChannelMapper;
import com.marcinwo.youtubeapi.demo.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ChannelController {

    private ChannelService channelService;
    private ChannelMapper channelMapper;

    @Autowired
    public ChannelController(ChannelService channelService, ChannelMapper channelMapper) {
        this.channelService = channelService;
        this.channelMapper = channelMapper;
    }

    @GetMapping("/channels")
    public List<ChannelDTO> getChannels() {
        return channelMapper.toChannelDTO(channelService.getChannels());
    }

    @GetMapping("/channels/{id}")
    public ChannelDTO getChannelById(@PathVariable Long id){
        return channelMapper.toChannelDTO(channelService.getChannelById(id));
    }

    @GetMapping("/users/{id}/channels")
    public List<ChannelDTO> getUserChannelsById(@PathVariable Long id){
       return channelMapper.toChannelDTO(channelService.getUserChannelsById(id));
     }


}
