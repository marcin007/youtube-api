package com.marcinwo.youtubeapi.demo.repository;

import com.marcinwo.youtubeapi.demo.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Before
    public void beforeEach() {
        commentRepository.deleteAll();
    }

    @Test
    public void given_filmHasComment_when_findAllByFilm_Id_then_returnComments() {
        User myUser = new User("Adam", "Kowalski", "akowalski", "pass", new HashSet<>(), new HashSet<>(), new HashSet<>());
        Film film = new Film(LocalDateTime.now(), "Sarnie zniwo", "Kino akjci", "www.qwe.pl", 22, new Channel("channel1", "desc1", myUser, new HashSet<>()), new HashSet<>(), new Category());
        Comment comment1 = new Comment(film, myUser, LocalDateTime.now(), LocalDateTime.now(), "content1", 11, 22, new HashSet<>());

        testEntityManager.persist(comment1);
        testEntityManager.flush();

        List<Comment> comments = commentRepository.findAllByFilm_Id(1L);

        assertThat(comments.get(0).getContent()).contains("content1");
        assertThat(comments.get(0)).isEqualTo(comment1);
    }

    @Test
    public void given_filmHasNoComment_when_findAllByFilm_Id_then_returnEmptyCommentList() {

        List<Comment> comment = commentRepository.findAllByFilm_Id(1L);

        assertThat(comment).isEmpty();
    }

}
