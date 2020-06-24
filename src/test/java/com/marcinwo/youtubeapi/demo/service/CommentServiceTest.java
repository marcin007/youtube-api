package com.marcinwo.youtubeapi.demo.service;

import com.marcinwo.youtubeapi.demo.ExampleData;
import com.marcinwo.youtubeapi.demo.dto.PatchCommentDTO;
import com.marcinwo.youtubeapi.demo.entity.Comment;
import com.marcinwo.youtubeapi.demo.repository.CommentRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

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
    public void given_commentExist_when_updateCommentById_then_OnlyContentAndUpdatedAtAreUpdated(){
//        given
        Comment comment = mock(Comment.class);

        LocalDateTime updatedAt = LocalDateTime.now().plusDays(1);
        PatchCommentDTO patchCommentDTO = new PatchCommentDTO("ela", updatedAt);

//        when
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(commentRepository.save(comment)).thenReturn(comment);

        commentService.updateCommentById(1L, patchCommentDTO);

//        then
        verify(comment, times(1)).setContent("ela");
        verify(comment, times(1)).setUpdatedAt(updatedAt);
        verifyNoMoreInteractions(comment);
    }

    @Test
    public void given_commentExist_when_updateCommentById_then_ContentAndUpdatedAtAreUpdated(){
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

        commentService.updateCommentById(1L, patchCommentDTO);

//        then
        assertThat(comment.getContent()).isEqualTo("ela");
        assertThat(comment.getUpdatedAt()).isEqualTo(updatedAt);
    }
}
