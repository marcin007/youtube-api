package com.marcinwo.youtubeapi.demo.mapper;


import com.marcinwo.youtubeapi.demo.dto.ChannelDTO;
import com.marcinwo.youtubeapi.demo.entity.Channel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ChannelMapper {

     @Mappings({
             @Mapping(target = "user", source = "channel.user.userName")
     })
     ChannelDTO toChannelDTO(Channel channel);
     List<ChannelDTO> toChannelDTO(Collection<Channel> channel);

}
