package com.marcinwo.youtubeapi.demo.service;

import com.marcinwo.youtubeapi.demo.entity.Category;
import com.marcinwo.youtubeapi.demo.entity.Channel;
import com.marcinwo.youtubeapi.demo.entity.Film;
import com.marcinwo.youtubeapi.demo.entity.User;
import com.marcinwo.youtubeapi.demo.exeption.FilmNotFoundException;
import com.marcinwo.youtubeapi.demo.repository.FilmRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmServiceTest {

    @MockBean
    private FilmRepository filmRepository;

    @Autowired
    private FilmService filmService;

    @Test
    public void given_filmsExist_when_getFilms_then_returnFilmList() {

        List<Film> filmList = List.of(
                new Film(
                        LocalDateTime.now(), "Matrix", "costam", "url", 120,
                        new Channel("channel1", "channel desc", new User(), new HashSet<>()), new HashSet<>(), new Category()
                ),
                new Film(
                        LocalDateTime.now(), "Matrix", "costam", "url", 120,
                        new Channel("channel1", "channel desc", new User(), new HashSet<>()), new HashSet<>(), new Category()
                ));

        when(filmRepository.findAll()).thenReturn(filmList);

        List<Film> films = filmService.getFilms();

        assertThat(films).isEqualTo(filmList);
    }

    @Test
    public void given_filmsNotExist_when_getFilms_then_returnEmptyFilmList(){
        List<Film> filmList = new ArrayList<>();
        when(filmRepository.findAll()).thenReturn(filmList);

        List<Film> films = filmService.getFilms();

        assertThat(films).isEmpty();
    }

    @Test
    public void given_filmExist_when_getFilm_then_returnOptionalOfFilm(){
        Film film = new Film(
                LocalDateTime.now(), "Matrix", "costam", "url", 120,
                new Channel("channel1", "channel desc", new User(), new HashSet<>()), new HashSet<>(), new Category()
        );
        film.setId(1L);

        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));

        Film serviceFilm = filmService.getFilm(1L);

        assertThat(serviceFilm).isEqualTo(film);
    }

    @Test(expected = FilmNotFoundException.class)
    public void given_filmNotExist_when_getFilm_then_filmNotFound(){

        when(filmRepository.findById(1L)).thenThrow(new FilmNotFoundException("film not found"));

        filmService.getFilm(1L);
    }

    @Test
    public void given_channelHasFilms_when_getFilmsByChannelId_then_returnFilmList(){
        Channel channel = new Channel("channel1", "channel desc", new User(), new HashSet<>());
        channel.setId(1L);

        List<Film> filmList = List.of(
                new Film(
                        LocalDateTime.now(), "Matrix", "costam", "url", 120,
                        channel, new HashSet<>(), new Category()
                ),
                new Film(
                        LocalDateTime.now(), "Matrix", "costam", "url", 120,
                        channel, new HashSet<>(), new Category()
                ));

        when(filmRepository.findAllByChannel_Id(1L)).thenReturn(filmList);

        List<Film> filmsByChannelId = filmService.getFilmsByChannelId(1L);

        assertThat(filmsByChannelId).isEqualTo(filmList);
    }

    @Test
    public void given_channelHasNoFilms_when_getFilmsByChannelId_then_returnEmptyFilmList(){
        List<Film> filmList = new ArrayList<>();

        when(filmRepository.findAllByChannel_Id(1L)).thenReturn(filmList);

        List<Film> filmsByChannelId = filmService.getFilmsByChannelId(1L);

        assertThat(filmsByChannelId).isEmpty();
    }



}
