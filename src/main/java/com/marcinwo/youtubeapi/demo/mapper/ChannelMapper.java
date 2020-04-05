package com.marcinwo.youtubeapi.demo.mapper;


import com.marcinwo.youtubeapi.demo.dto.ChannelDTO;
import com.marcinwo.youtubeapi.demo.entity.Channel;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ChannelMapper {

     ChannelDTO toChannelDTO(Channel channel);
     List<ChannelDTO> toChannelDTO(Collection<Channel> channel);

}
