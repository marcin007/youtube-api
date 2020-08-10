package com.marcinwo.youtubeapi.demo.service;

import com.marcinwo.youtubeapi.demo.ExampleData;
import com.marcinwo.youtubeapi.demo.entity.Comment;
import com.marcinwo.youtubeapi.demo.entity.Reply;
import com.marcinwo.youtubeapi.demo.repository.ReplyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReplyServiceTest {


    @MockBean
    private ReplyRepository replyRepository;


    @Autowired
    private ReplyService replyService;


    @Test
    public void given_repliesExist_when_getReplies_then_returnReplyList() {
        List<Reply> replyList = ExampleData.replies();
        when(replyRepository.findAll()).thenReturn(replyList);

        List<Reply> replies = replyService.getReplies();

        assertThat(replies).isEqualTo(replyList);
    }

    @Test
    public void given_repliesNotExist_when_getReplies_then_returnEmptyList() {
        List<Reply> replyList = new ArrayList<>();
        when(replyRepository.findAll()).thenReturn(replyList);

        List<Reply> replies = replyService.getReplies();

        assertThat(replies).isEmpty();
    }

    @Test
    public void given_commentHasReplies_when_getRepliesByCommentId_then_returnReplyList() {
        Comment comment = new Comment();
        comment.setId(1L);
        List<Reply> replyList = ExampleData.replies();
        replyList.get(0).setComment(comment);
        replyList.get(1).setComment(comment);

        when(replyRepository.findAllByCommentId(1L)).thenReturn(replyList);

        List<Reply> repliesByCommentId = replyService.getRepliesByCommentId(1L);

        assertThat(repliesByCommentId).isEqualTo(replyList);
    }

    @Test
    public void given_commentHasNoReplies_when_getRepliesByCommentId_then_returnEmptyReplyList() {
        List<Reply> replyList = new ArrayList<>();
        when(replyRepository.findAllByCommentId(1L)).thenReturn(replyList);

        List<Reply> repliesByCommentId = replyService.getRepliesByCommentId(1L);

        assertThat(repliesByCommentId).isEmpty();
    }

    @Test
    public void given_replyExist_when_postReply_then_saveReply() {
        Reply reply = ExampleData.reply();

        when(replyRepository.save(reply)).thenReturn(reply);

        Reply postReply = replyService.postReply(reply);

        assertThat(postReply).isEqualTo(reply);
    }


}
