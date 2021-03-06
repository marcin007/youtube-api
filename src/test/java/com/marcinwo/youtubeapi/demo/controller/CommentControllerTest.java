package com.marcinwo.youtubeapi.demo.controller;

import com.marcinwo.youtubeapi.demo.JsonUtils;
import com.marcinwo.youtubeapi.demo.dto.CommentDTO;
import com.marcinwo.youtubeapi.demo.entity.Comment;
import com.marcinwo.youtubeapi.demo.entity.Film;
import com.marcinwo.youtubeapi.demo.entity.Reply;
import com.marcinwo.youtubeapi.demo.entity.User;
import com.marcinwo.youtubeapi.demo.mapper.CommentMapper;
import com.marcinwo.youtubeapi.demo.service.CommentService;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @MockBean
    private CommentMapper commentMapper;

    @Test
    public void getCommentTest() throws Exception {
        //given
        List<Comment> comment = List.of(
                new Comment(new Film(), new User(), LocalDateTime.now(), LocalDateTime.now(), "Komentarz 1", 233, 32, Set.of(new Reply())),
                new Comment(new Film(), new User(), LocalDateTime.now(), LocalDateTime.now(), "Komentarz 1", 1, 2, Set.of(new Reply())),
                new Comment(new Film(), new User(), LocalDateTime.now(), LocalDateTime.now(), "Komentarz 1", 2, 66, Set.of(new Reply()))
        );

        List<CommentDTO> commentDTOS = List.of(
                new CommentDTO(2L, 2L, 1L, "Komentarz 1 DTO", 22, 33),
                new CommentDTO(3L, 3L, 3L, "Komentarz 2 DTO", 22, 33),
                new CommentDTO(1L, 1L, 2L,
                        LocalDateTime.of(2220, Month.FEBRUARY, 22, 11, 11),
                        LocalDateTime.of(1986, Month.APRIL, 8, 12, 30),
                        "Komentarz 3 DTO", 22, 33)
        );

        //when
        when(commentService.getComments()).thenReturn(comment);
        when(commentMapper.toCommentDTO(anyCollection())).thenReturn(commentDTOS);

        System.out.println(JsonUtils.toJsonString(commentDTOS));

        //then
        mockMvc.perform(get("/comments"))
                .andDo(print())
                .andExpect(content().string(CoreMatchers.containsString("K")))
                .andExpect(content().json(JsonUtils.toJsonString(commentDTOS)));
    }

    @Test
    public void getCommentsByFilmId() throws Exception {


        List<Comment> comments = List.of(
                new Comment(new Film(), new User(), LocalDateTime.now(), LocalDateTime.now(), "con1", 12, 34, new HashSet<>()),
                new Comment(new Film(), new User(), LocalDateTime.now(), LocalDateTime.now(), "con2", 12, 34, new HashSet<>()));

        List<CommentDTO> commentDTO = List.of(
                new CommentDTO(2L, 3L, 1L, "con1", 22, 33),
                new CommentDTO(2L, 3L, 1L, "con2", 22, 33)
        );

        when(commentService.getCommentsByFilmId(1L)).thenReturn(comments);
        when(commentMapper.toCommentDTO(comments)).thenReturn(commentDTO);

        mockMvc.perform(get("/films/1/comments"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtils.toJsonString(commentDTO)));

    }

}
