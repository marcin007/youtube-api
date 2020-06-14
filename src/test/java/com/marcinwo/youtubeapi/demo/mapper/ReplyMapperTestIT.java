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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReplyMapperTestIT {

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;


    @Test(expected = UserNotFoundException.class)
    public void when_toReplyEntityWithoutExistingUserAndWithoutExistingComment_then_UserNotFoundException() {
        userRepository.deleteAll();
        commentRepository.deleteAll();

        ReplyDTO replyDTO = new ReplyDTO(99L, 99L, "lala", 33, 33);
        replyMapper.toReplyEntity(replyDTO);
    }

    @Test(expected = CommentNotFoundException.class)
    public void when_toReplyEntityWithoutExistingComment_then_CommentNotFoundException() {
        User user = ExampleData.user();

        userRepository.deleteAll();
        commentRepository.deleteAll();
        userRepository.save(user);

        Long userId = user.getId();

        ReplyDTO replyDTO = new ReplyDTO(userId, 99L, "lala", 33, 33);
        replyMapper.toReplyEntity(replyDTO);
    }

    @Test(expected = UserNotFoundException.class)
    public void when_toReplyEntityWithoutExistingUser_then_UserNotFoundException() {
        Comment comment = ExampleData.comment();

        userRepository.deleteAll();
        commentRepository.deleteAll();
        commentRepository.save(comment);

        Long commentId = comment.getId();

        ReplyDTO replyDTO = new ReplyDTO(99L, commentId, "lala", LocalDateTime.now(), 33, 33);
        replyMapper.toReplyEntity(replyDTO);
    }

    @Test
    public void when_toReplyEntityWithExistingCommentAndUser_then_MappingIsFineTest() {
        User user = ExampleData.user();
        Comment comment = ExampleData.comment();

        userRepository.deleteAll();
        commentRepository.deleteAll();

        userRepository.save(user);
        commentRepository.save(comment);

        Long userId = user.getId();
        Long commentId = comment.getId();
        ReplyDTO replyDTO = new ReplyDTO(userId, commentId, "lala", LocalDateTime.now(), 33, 33);

        Reply reply = replyMapper.toReplyEntity(replyDTO);
        assertEquals("lala", reply.getContent());
        //assertEquals(LocalDateTime.now() ,reply.getCreatedAt());
    }

    @Test
    public void toReplyDtoTest() {
        Reply reply = new Reply(new User(), new Comment(), "fiki", LocalDateTime.now(), LocalDateTime.now(), 22, 44);

        ReplyDTO replyDTO = replyMapper.toReplyDTO(reply);

        assertEquals(reply.getLikes(), replyDTO.getLikes());
        assertEquals(reply.getDislikes(), replyDTO.getDislikes());
        assertEquals(reply.getContent(), replyDTO.getContent());
    }


    @Test
    public void toListReplyDtoTest() {
        List<Reply> replies = List.of(new Reply(new User(), new Comment(), "fiki", LocalDateTime.now(), LocalDateTime.now(), 22, 44),
                new Reply(new User(), new Comment(), "fiki", LocalDateTime.now(), LocalDateTime.now(), 22, 44));

        List<ReplyDTO> replyDTOS = new ArrayList<>(replyMapper.toReplyDTO(replies));

        assertEquals(replyDTOS.get(0).getContent(), replies.get(0).getContent());
        assertEquals(replyDTOS.get(0).getCommentId(), replies.get(0).getComment().getId());
        assertEquals(replyDTOS.get(0).getDislikes(), replies.get(0).getDislikes());
        assertEquals(replyDTOS.get(0).getLikes(), replies.get(0).getLikes());
        assertEquals(replyDTOS.get(0).getUserId(), replies.get(0).getUser().getId());
        assertEquals(replyDTOS.get(0).getCreatedAt(), replies.get(0).getCreatedAt());

        assertEquals(replyDTOS.get(1).getContent(), replies.get(1).getContent());
        assertEquals(replyDTOS.get(1).getCommentId(), replies.get(1).getComment().getId());
        assertEquals(replyDTOS.get(1).getDislikes(), replies.get(1).getDislikes());
        assertEquals(replyDTOS.get(1).getLikes(), replies.get(1).getLikes());
        assertEquals(replyDTOS.get(1).getUserId(), replies.get(1).getUser().getId());
        assertEquals(replyDTOS.get(1).getCreatedAt(), replies.get(1).getCreatedAt());

    }

}
