package com.marcinwo.youtubeapi.demo.mapper;

import com.marcinwo.youtubeapi.demo.ExampleData;
import com.marcinwo.youtubeapi.demo.dto.ReplyDTO;
import com.marcinwo.youtubeapi.demo.entity.Comment;
import com.marcinwo.youtubeapi.demo.entity.Reply;
import com.marcinwo.youtubeapi.demo.entity.User;
import com.marcinwo.youtubeapi.demo.exeption.CommentNotFoundException;
import com.marcinwo.youtubeapi.demo.exeption.UserNotFoundException;
import com.marcinwo.youtubeapi.demo.repository.CommentRepository;
import com.marcinwo.youtubeapi.demo.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReplyMapperTest {

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test(expected = UserNotFoundException.class)
    public void when_toReplyEntityWithoutExistingUserAndWithoutExistingComment_then_UserNotFoundException(){
        userRepository.deleteAll();
        commentRepository.deleteAll();

        ReplyDTO replyDTO = new ReplyDTO(99L, 99L,"lala");
        replyMapper.toReplyEntity(replyDTO);
    }

    @Test(expected = CommentNotFoundException.class)
    public void when_toReplyEntityWithoutExistingComment_then_CommentNotFoundException(){
        User user = ExampleData.user();

        userRepository.deleteAll();
        commentRepository.deleteAll();
        userRepository.save(user);

        Long userId = user.getId();

        ReplyDTO replyDTO = new ReplyDTO(userId, 99L,"lala");
        replyMapper.toReplyEntity(replyDTO);
    }

    @Test(expected = UserNotFoundException.class)
    public void when_toReplyEntityWithoutExistingUser_then_UserNotFoundException(){
        Comment comment = ExampleData.comment();

        userRepository.deleteAll();
        commentRepository.deleteAll();
        commentRepository.save(comment);

        Long commentId = comment.getId();

        ReplyDTO replyDTO = new ReplyDTO(99L, commentId,"lala", LocalDateTime.now());
        replyMapper.toReplyEntity(replyDTO);
    }

    @Test
    public void when_toReplyEntityWithExistingCommentAndUser_then_MappingIsFineTest(){
        User user = ExampleData.user();
        Comment comment = ExampleData.comment();

        userRepository.deleteAll();
        commentRepository.deleteAll();

        userRepository.save(user);
        commentRepository.save(comment);

        Long userId = user.getId();
        Long commentId = comment.getId();
        ReplyDTO replyDTO = new ReplyDTO(userId, commentId,"lala", LocalDateTime.now());

        Reply reply = replyMapper.toReplyEntity(replyDTO);
        //assertEquals("lala", reply.getContent());

    }

}
