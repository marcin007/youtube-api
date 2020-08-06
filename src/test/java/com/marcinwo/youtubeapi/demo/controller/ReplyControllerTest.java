package com.marcinwo.youtubeapi.demo.controller;

import com.marcinwo.youtubeapi.demo.JsonUtils;
import com.marcinwo.youtubeapi.demo.dto.ReplyDTO;
import com.marcinwo.youtubeapi.demo.entity.Comment;
import com.marcinwo.youtubeapi.demo.entity.Film;
import com.marcinwo.youtubeapi.demo.entity.Reply;
import com.marcinwo.youtubeapi.demo.entity.User;
import com.marcinwo.youtubeapi.demo.mapper.ReplyMapper;
import com.marcinwo.youtubeapi.demo.service.ReplyService;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ReplyController.class)
public class ReplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReplyService replyService;

    @MockBean
    private ReplyMapper replyMapper;

    @Test
    public void getRepliesTest() throws Exception {

        //given
        List<Reply> replies = List.of(
                new Reply(new User(), new Comment(), "Content 1", LocalDateTime.now(), LocalDateTime.now(), 10, 32),
                new Reply(new User(), new Comment(), "Content 2", LocalDateTime.now(), LocalDateTime.now(), 4, 3),
                new Reply(new User(), new Comment(), "Content 3", LocalDateTime.now(), LocalDateTime.now(), 77, 222)
        );

        List<ReplyDTO> replyDTOS = List.of(
                new ReplyDTO(2L, 2L, "uiop", 33, 33),
                new ReplyDTO(1L, 1L, "qwer", 33, 33),
                new ReplyDTO(3L, 3L, "asd", 33, 33)
        );
        //when
        when(replyService.getReplies()).thenReturn(replies);
        when(replyMapper.toReplyDTO(anyCollection())).thenReturn(replyDTOS);

        //then
        mockMvc.perform(get("/replies"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(CoreMatchers.containsString("asd")))
                .andExpect(content().json(JsonUtils.toJsonString(replyDTOS)));
    }

    @Test//todo ok?
    public void getRepliesByCommentIdTest() throws Exception {

        Comment comment = new Comment(new Film(), new User(), LocalDateTime.now(), LocalDateTime.now(), "Comment1", 1, 2, new HashSet<>());
        comment.setId(1L);
        List<Reply> replies = List.of(
                new Reply(new User(), comment, "content1", LocalDateTime.now(), LocalDateTime.now(), 10, 32),
                new Reply(new User(), comment, "content2", LocalDateTime.now(), LocalDateTime.now(), 10, 32)
        );

        List<ReplyDTO> replyDTOS = List.of(
                new ReplyDTO(1L, comment.getId(), "content1", 12, 34),
                new ReplyDTO(2L, comment.getId(), "content2", 56, 78));


        when(replyService.getRepliesByCommentId(1L)).thenReturn(replies);
        when(replyMapper.toReplyDTO(replies)).thenReturn(replyDTOS);

        mockMvc.perform(get("/comments/1/replies"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("content1"))
                .andExpect(jsonPath("$[0].likes").value(12))
                .andExpect(jsonPath("$[0].dislikes").value(34))
                .andExpect(jsonPath("$[0].commentId").value(1))
                .andExpect(jsonPath("$[0].userId").value(1))

                .andExpect(jsonPath("$[1].content").value("content2"))
                .andExpect(jsonPath("$[1].likes").value(56))
                .andExpect(jsonPath("$[1].dislikes").value(78))
                .andExpect(jsonPath("$[1].commentId").value(1))
                .andExpect(jsonPath("$[1].userId").value(2))

                .andExpect(content().json(JsonUtils.toJsonString(replyDTOS)));
    }

    @Test//todo ok?
    public void postReplyTest() throws Exception {
        ReplyDTO replyDTOBeforeSave = new ReplyDTO(22L, 11L, "content", 22, 22);
        ReplyDTO replyDTOAfterSave = new ReplyDTO(1L, 22L, 11L, "content", 22, 22);

        Reply reply = new Reply();
        when(replyMapper.toReplyEntity(any(ReplyDTO.class))).thenReturn(reply);
        when(replyService.postReply(reply)).thenReturn(reply);
        when(replyMapper.toReplyDTO(reply)).thenReturn(replyDTOAfterSave);

        mockMvc.perform(post("/replies")
                .content(JsonUtils.toJsonString(replyDTOBeforeSave))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("content"))
                .andExpect(jsonPath("$.likes").value(22))
                .andExpect(jsonPath("$.dislikes").value(22))
                .andExpect(jsonPath("$.commentId").value(11))
                .andExpect(jsonPath("$.userId").value(22))
                .andExpect(content().json(JsonUtils.toJsonString(replyDTOAfterSave)));

    }
}
