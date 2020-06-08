package com.marcinwo.youtubeapi.demo.mapper;

import com.marcinwo.youtubeapi.demo.ExampleData;
import com.marcinwo.youtubeapi.demo.dto.UserWatchedFilmDTO;
import com.marcinwo.youtubeapi.demo.entity.Film;
import com.marcinwo.youtubeapi.demo.entity.User;
import com.marcinwo.youtubeapi.demo.entity.UserWatchedFilm;
import com.marcinwo.youtubeapi.demo.exeption.FilmNotFoundException;
import com.marcinwo.youtubeapi.demo.exeption.UserNotFoundException;
import com.marcinwo.youtubeapi.demo.repository.FilmRepository;
import com.marcinwo.youtubeapi.demo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserWatchedFilmMapperTestIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private UserWatchedFilmMapper userWatchedFilmMapper;

    @Test(expected = FilmNotFoundException.class)
    public void when_toUserWatchedFilmEntityWithoutExistingFilmAndWithoutExistingUser_then_FilmNotFoundException(){
        filmRepository.deleteAll();
        userRepository.deleteAll();

        UserWatchedFilmDTO userWatchedFilmDTO = new UserWatchedFilmDTO(1L, 1L,
                new BigDecimal("9.5"), LocalDateTime.now(), LocalDateTime.now());
        userWatchedFilmMapper.toUserWatchedFilmEntity(userWatchedFilmDTO);
    }

    @Test(expected = FilmNotFoundException.class)
    public void when_toUserWatchedFilmEntityWithoutExistingFilmAndWithExistingUser_then_FilmNotFoundException(){
        filmRepository.deleteAll();
        userRepository.deleteAll();

        User user = ExampleData.user();
        userRepository.save(user);

        Long userId = user.getId();

        UserWatchedFilmDTO userWatchedFilmDTO = new UserWatchedFilmDTO(userId, 1L, new BigDecimal("9.5"), LocalDateTime.now(), LocalDateTime.now());

        userWatchedFilmMapper.toUserWatchedFilmEntity(userWatchedFilmDTO);
    }

    @Test(expected = UserNotFoundException.class)
    public void when_toUserWatchedFilmEntityWithExistingFilmAndWithoutExistingUser_then_UserNotFoundException(){
        filmRepository.deleteAll();
        userRepository.deleteAll();

        Film film = ExampleData.film();
        filmRepository.save(film);

        Long filmId = film.getId();

        UserWatchedFilmDTO userWatchedFilmDTO = new UserWatchedFilmDTO(99L, filmId, new BigDecimal("9.5"), LocalDateTime.now(), LocalDateTime.now());

        userWatchedFilmMapper.toUserWatchedFilmEntity(userWatchedFilmDTO);
    }

    @Test
    public void when_toUserWatchedFilmEntityWithExistingFilmAndWithExistingUser_then_MappingIsFineTest(){
        filmRepository.deleteAll();
        userRepository.deleteAll();

        Film film = ExampleData.film();
        filmRepository.save(film);
        User user = ExampleData.user();
        userRepository.save(user);

        Long filmId = film.getId();
        Long userId = user.getId();

        UserWatchedFilmDTO userWatchedFilmDTO = new UserWatchedFilmDTO(userId, filmId, new BigDecimal("9.5"), LocalDateTime.now(), LocalDateTime.now());

        UserWatchedFilm userWatchedFilm = userWatchedFilmMapper.toUserWatchedFilmEntity(userWatchedFilmDTO);
        assertEquals(new BigDecimal("9.5"), userWatchedFilm.getTimeSpentForWatching());
        assertEquals(user.getId(), userWatchedFilm.getUser().getId());
    }

    @Test
    public void toUserWatchedFilmDTOTest(){
        UserWatchedFilm userWatchedFilm = new UserWatchedFilm(new User(), new Film(), new BigDecimal("44"), LocalDateTime.now(), LocalDateTime.now());

        UserWatchedFilmDTO userWatchedFilmDTO = userWatchedFilmMapper.toUserWatchedFilmDTO(userWatchedFilm);

        assertEquals(new BigDecimal("44"), userWatchedFilmDTO.getTimeSpentForWatching());

    }
}
