package com.marcinwo.youtubeapi.demo.mapper;

import com.marcinwo.youtubeapi.demo.ExampleData;
import com.marcinwo.youtubeapi.demo.dto.CommentDTO;
import com.marcinwo.youtubeapi.demo.entity.Comment;
import com.marcinwo.youtubeapi.demo.entity.Film;
import com.marcinwo.youtubeapi.demo.entity.Reply;
import com.marcinwo.youtubeapi.demo.entity.User;
import com.marcinwo.youtubeapi.demo.exeption.FilmNotFoundException;
import com.marcinwo.youtubeapi.demo.exeption.UserNotFoundException;
import com.marcinwo.youtubeapi.demo.repository.FilmRepository;
import com.marcinwo.youtubeapi.demo.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentMapperTestIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private CommentMapper commentMapper;

    @Test(expected = UserNotFoundException.class)
    public void when_toCommentEntityWithoutExistingUserAndWithoutExistingFilm_then_UserNotFoundExceptionTest() {
        userRepository.deleteAll();
        filmRepository.deleteAll();

        CommentDTO commentDTO = new CommentDTO(22L, 22L, 33L, "ds", 11, 11);
        commentMapper.toCommentEntity(commentDTO);
    }

    @Test(expected = FilmNotFoundException.class)
    public void when_toCommentEntityWithoutExistingFilm_then_UserNotFoundExceptionTest() {
        User user = ExampleData.user();

        filmRepository.deleteAll();
        userRepository.deleteAll();
        userRepository.save(user);

        Long userId = user.getId();
        CommentDTO commentDTO = new CommentDTO(1L, 1L, userId, "ds", 11, 11);

        commentMapper.toCommentEntity(commentDTO);
    }

    @Test(expected = UserNotFoundException.class)
    public void when_toCommentEntityWithoutExistingUser_then_UserNotFoundExceptionTest() {
        User user = ExampleData.user();
        Film film = ExampleData.film();

        userRepository.deleteAll();
        userRepository.save(user);

        filmRepository.deleteAll();
        filmRepository.save(film);

        Long filmId = film.getId();
        CommentDTO commentDTO = new CommentDTO(1L, filmId, 99L, "ds", 11, 22);

        commentMapper.toCommentEntity(commentDTO);
    }

    @Test
    public void when_toCommentEntityWithExistingFilmAndUser_then_MappingIsFineTest() {
        User user = ExampleData.user();
        Film film = ExampleData.film();

        userRepository.deleteAll();
        userRepository.save(user);

        filmRepository.deleteAll();
        filmRepository.save(film);

        Long filmId = film.getId();
        Long userId = user.getId();
        CommentDTO commentDTO = new CommentDTO(1L, filmId, userId, "ds", 11, 22);

        Comment comment = commentMapper.toCommentEntity(commentDTO);
        assertEquals(11, comment.getLikes());
        assertEquals(22, comment.getDislikes());
    }

    @Test
    public void toCommentDtoTest(){

        Comment comment = new Comment(new Film(), new User(), LocalDateTime.now(), LocalDateTime.now(),
                "ala", 44, 55, Set.of(new Reply()));
        CommentDTO commentDTO = commentMapper.toCommentDTO(comment);

        assertEquals(44, commentDTO.getLikes());
        assertEquals(55, commentDTO.getDislikes());
        assertEquals("ala", commentDTO.getContent());
    }

}
