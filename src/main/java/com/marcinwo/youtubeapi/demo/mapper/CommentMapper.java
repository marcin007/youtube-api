package com.marcinwo.youtubeapi.demo.mapper;

import com.marcinwo.youtubeapi.demo.dto.CommentDTO;
import com.marcinwo.youtubeapi.demo.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CommentMapper {


    @Mappings({
            @Mapping(target = "film", source = "film.title"),
            @Mapping(target = "user", source = "user.userName")
    })
    CommentDTO toCommentDTO(Comment comment);
    List<CommentDTO> toCommentDTO(Collection<Comment> comments);

    @Mappings({
            @Mapping(target = "film.title", source = "commentDTO.film"),
            @Mapping(target = "user.userName", source = "commentDTO.user")
    })
    Comment toCommentEntity(CommentDTO commentDTO);

}
