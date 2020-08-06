package com.marcinwo.youtubeapi.demo.repository;


import com.marcinwo.youtubeapi.demo.entity.Comment;
import com.marcinwo.youtubeapi.demo.entity.Reply;
import com.marcinwo.youtubeapi.demo.entity.User;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ReplyRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ReplyRepository replyRepository;

    @Before
    public void beforeEach(){
        replyRepository.deleteAll();
    }

    @Test//ok
    public void whenFindByCommentId_thenReturnReply(){
        Reply reply =  new Reply(new User(), new Comment(), "content", LocalDateTime.now(), LocalDateTime.now(), 22,22);

        testEntityManager.persist(reply);
        testEntityManager.flush();

        String content = "content";
        List<Reply> replies = replyRepository.findAllByCommentId(1L);

        assertThat(replies.get(0).getContent().equals("content"));
        assertThat(replies).hasSize(1);
        assertThat(replies.get(0)).isEqualTo(reply);
    }

    @Test//ok
    public void whenFindByUsername_thenReturnReply(){
        Reply reply =  new Reply(new User("jacek", "jakcowski", "relic", "qwe"), new Comment(), "con", LocalDateTime.now(), LocalDateTime.now(), 22,22);
        Reply reply2 =  new Reply(new User("kkk", "aaa", "maxkarim", "qwe"), new Comment(), "con", LocalDateTime.now(), LocalDateTime.now(), 22,22);

        testEntityManager.persist(reply);
        testEntityManager.persist(reply2);
        testEntityManager.flush();

        String username = "relic";
        List<Reply> replies = replyRepository.findAllByUser_UserName(username);

        assertThat(replies.get(0).getUser().getUserName().equals(username));
        assertThat(replies.get(1).getUser().getUserName().equals(username));
    }

    @Test//ok
    public void given_commentHasNoReply_when_findAllByCommentId_then_returnEmptyReplyList(){
        List<Reply> replies = replyRepository.findAllByCommentId(1L);

        assertThat(replies).isNotEmpty();
    }

    @Test
    public void given_replyHasNoUserWithThatUserName_when_findAllByUserName_then_returnEmptyReplyList(){
        List<Reply> replies = replyRepository.findAllByUser_UserName("username");

        assertThat(replies).isEmpty();
    }

}
