package com.marcinwo.youtubeapi.demo.service;

import com.marcinwo.youtubeapi.demo.ExampleData;
import com.marcinwo.youtubeapi.demo.dto.PatchCommentDTO;
import com.marcinwo.youtubeapi.demo.entity.*;
import com.marcinwo.youtubeapi.demo.exeption.CommentNotFoundException;
import com.marcinwo.youtubeapi.demo.repository.CommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @MockBean
    private CommentRepository commentRepository;


    @Test
    public void given_commentsExist_when_getComments_then_returnCommentsList() {
        List<Comment> commentList = ExampleData.comments();

        when(commentRepository.findAll()).thenReturn(commentList);

        List<Comment> comments = commentService.getComments();

        assertThat(comments).isEqualTo(commentList);

    }

    @Test
    public void given_commentsNotExist_when_getComments_then_returnEmptyCommentsList() {
        List<Comment> commentList = new ArrayList<>();

        when(commentRepository.findAll()).thenReturn(commentList);

        List<Comment> comments = commentService.getComments();

        assertThat(comments).isEmpty();
    }

    @Test
    public void given_filmHasComments_when_getCommentsByFilmId_then_returnCommentList() {
        Film film = new Film(
                LocalDateTime.now(), "Matrix", "costam", "url", 120,
                new Channel("channel1", "channel desc", new User(), new HashSet<>()), new HashSet<>(), new Category()
        );
        film.setId(1L);
        List<Comment> commentList = List.of(
                new Comment(
                        film,
                        new User(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "comment content1",
                        11,
                        22,
                        Set.of(new Reply())),
                new Comment(
                        film,
                        new User(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "comment content2",
                        44,
                        55,
                        Set.of(new Reply())));


        when(commentRepository.findAllByFilm_Id(1L)).thenReturn(commentList);

        List<Comment> commentsByFilmId = commentService.getCommentsByFilmId(1L);

        assertThat(commentsByFilmId).isEqualTo(commentList);
    }

    @Test
    public void given_filmHasNoChannels_when_getCommentsByFilmId_then_returnEmptyChannelList() {
        List<Comment> commentList = new ArrayList<>();

        when(commentRepository.findAllByFilm_Id(1L)).thenReturn(commentList);

        List<Comment> commentsByFilmId = commentService.getCommentsByFilmId(1L);

        assertThat(commentsByFilmId).isEmpty();
    }

    @Test
    public void given_commentExist_when_save_then_commentSaved() {
        Comment comment = ExampleData.comment();

        when(commentRepository.save(comment)).thenReturn(comment);

        Comment commentSaved = commentService.save(comment);

        assertThat(commentSaved).isEqualTo(comment);

    }

    @Test
    public void given_commentExist_when_getCommentById_then_returnOptionalOfComment() {
        Comment comment = ExampleData.comment();

        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        Comment commentById = commentService.getCommentById(1L);

        assertThat(commentById).isEqualTo(comment);
    }

    @Test(expected = CommentNotFoundException.class)
    public void given_commentNotExist_when_getCommentById_then_returnCommentNotExist() {
        when(commentRepository.findById(1L)).thenThrow(new CommentNotFoundException("Comment not found"));

        commentService.getCommentById(1L);
    }

    @Test// - simply behavioral test
    public void given_commentExist_when_updateCommentById_then_OnlyContentAndUpdatedAtAreUpdated() {
//        given
        Comment comment = mock(Comment.class);

        LocalDateTime updatedAt = LocalDateTime.now().plusDays(1);
        PatchCommentDTO patchCommentDTO = new PatchCommentDTO("ela", updatedAt);

//        when
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(commentRepository.save(comment)).thenReturn(comment);

        Comment commentUpdated = commentService.updateCommentById(1L, patchCommentDTO);

//        then
        verify(comment, times(1)).setContent("ela");
        verify(comment, times(1)).setUpdatedAt(updatedAt);
        verifyNoMoreInteractions(commentUpdated);
    }

    @Test
    public void given_commentExist_when_updateCommentById_then_ContentAndUpdatedAtAreUpdated() {
//        given
        Comment comment = ExampleData.comment2();
        comment.setId(1L);
        comment.setContent("ala");
        comment.setUpdatedAt(LocalDateTime.now());

        LocalDateTime updatedAt = LocalDateTime.now().plusDays(1);
        PatchCommentDTO patchCommentDTO = new PatchCommentDTO("ela", updatedAt);

//        when
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(commentRepository.save(comment)).thenReturn(comment);

        Comment commentUpdated = commentService.updateCommentById(1L, patchCommentDTO);

//        then
        assertThat(commentUpdated.getContent()).isEqualTo("ela");
        assertThat(commentUpdated.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test//todo
    public void given_commentExist_when_deleteCommentById_commentNotExist() {


    }


}
