package com.marcinwo.youtubeapi.demo.mapper;

import com.marcinwo.youtubeapi.demo.dto.ReplyDTO;
import com.marcinwo.youtubeapi.demo.entity.Comment;
import com.marcinwo.youtubeapi.demo.entity.Reply;
import com.marcinwo.youtubeapi.demo.entity.User;
import com.marcinwo.youtubeapi.demo.exeption.CommentNotFoundException;
import com.marcinwo.youtubeapi.demo.exeption.UserNotFoundException;
import com.marcinwo.youtubeapi.demo.repository.CommentRepository;
import com.marcinwo.youtubeapi.demo.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class ReplyMapper {

    private UserRepository userRepository;
    private CommentRepository commentRepository;

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Mappings({
            @Mapping(target = "userId", source ="user.id"),
            @Mapping(target = "commentId", source ="comment.id")
    })
    public abstract ReplyDTO toReplyDTO(Reply reply);
    public abstract Set<ReplyDTO> toReplyDTO(Collection<Reply> reply);

    @Mappings({
            @Mapping(target = "content", source ="content"),
    })
    public Reply toReplyEntity(ReplyDTO replyDTO){
        Reply reply = new Reply();

        User user = userRepository.findById(replyDTO.getUserId()).orElseThrow(()-> new UserNotFoundException("User nor found."));
        reply.setUser(user);

        Comment comment = commentRepository.findById(replyDTO.getCommentId()).orElseThrow(()-> new CommentNotFoundException("Comment nor found."));
        reply.setComment(comment);

        return reply;
    }
}

