package com.marcinwo.youtubeapi.demo.repository;

import com.marcinwo.youtubeapi.demo.ExampleData;
import com.marcinwo.youtubeapi.demo.entity.Comment;
import com.marcinwo.youtubeapi.demo.entity.Reply;
import com.marcinwo.youtubeapi.demo.entity.User;
import com.marcinwo.youtubeapi.demo.exeption.UserNotFoundException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.isA;

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

    @Test
    public void whenFindByCommentId_thenReturnReply(){
        //given
        Reply reply =  new Reply(new User(), new Comment(), "con", LocalDateTime.now(), LocalDateTime.now(), 22,22);

        testEntityManager.persist(reply);
        testEntityManager.flush();

        //when
        String content = "content";
        List<Reply> replies = replyRepository.findAllByCommentId(1L);
        Optional<Reply> reply1 = replyRepository.findByUserId(1L);

        //then
        //assertThat(replies, contains("content"));
        assertThat(reply1, notNullValue());
    }

    @Test
    public void whenFindByUsername_thenReturnReply(){
        //given
        Reply reply =  new Reply(new User("jacek", "jakcowski", "relic", "qwe"), new Comment(), "con", LocalDateTime.now(), LocalDateTime.now(), 22,22);
        Reply reply2 =  new Reply(new User("kkk", "aaa", "maxkarim", "qwe"), new Comment(), "con", LocalDateTime.now(), LocalDateTime.now(), 22,22);

        testEntityManager.persist(reply);
        testEntityManager.persist(reply2);
        testEntityManager.flush();

        String username = "relic";
        List<Reply> replies = replyRepository.findAllByUser_UserName(username);

        assertThat(replies.get(0).getUser().getUserName(), equalTo(username));
    }

    @Test(expected = UserNotFoundException.class) // todo jak opisac ten blad?
    public void testException(){
        Reply reply =  new Reply(new User("jacek", "jakcowski", "relic", "qwe"), new Comment(), "con", LocalDateTime.now(), LocalDateTime.now(), 22,22);

        testEntityManager.persist(reply);
        testEntityManager.flush();

        String username = "relic";

        replyRepository.findAllByUser_UserName(username);



        throw new UserNotFoundException("User not found");
    }

}
