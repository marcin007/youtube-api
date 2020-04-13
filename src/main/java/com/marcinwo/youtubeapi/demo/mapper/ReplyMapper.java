package com.marcinwo.youtubeapi.demo.mapper;

import com.marcinwo.youtubeapi.demo.dto.ReplyDTO;
import com.marcinwo.youtubeapi.demo.entity.Reply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ReplyMapper {

    @Mappings({
            @Mapping(target = "user", source ="reply.user.userName"),
            @Mapping(target = "createdAt", source = "createdAt")
    })
    ReplyDTO toReplyDTO(Reply reply);

    Set<ReplyDTO> toReplyDTO(Collection<Reply> reply);

    @Mappings({
            @Mapping(target = "user.userName", source ="replyDTO.user"),
            @Mapping(target = "createdAt", source = "replyDTO.createdAt")
    })
    Reply toReplyEntity(ReplyDTO replyDTO);
}

