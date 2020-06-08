package com.marcinwo.youtubeapi.demo.mapper;

import com.marcinwo.youtubeapi.demo.dto.FilmDTO;
import com.marcinwo.youtubeapi.demo.entity.Category;
import com.marcinwo.youtubeapi.demo.entity.Channel;
import com.marcinwo.youtubeapi.demo.entity.Comment;
import com.marcinwo.youtubeapi.demo.entity.Film;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class FilmMapperTest {

    private FilmMapper filmMapper = new FilmMapperImpl();

    @Test
    public void toFilmDtoTest(){
        Film film = new Film(LocalDateTime.now(), "Sarnie zniwo", "Git produkcja", "www.fakamaka.co",
                22, new Channel(), Set.of(new Comment()), new Category());


        FilmDTO filmDTO = filmMapper.toFilmDTO(film);

        assertEquals(filmDTO.getUploadDate(), film.getUploadDate());
        assertEquals(filmDTO.getTitle(), film.getTitle());
        assertEquals(filmDTO.getDescription(), film.getDescription());
        assertEquals(filmDTO.getUrl(), film.getUrl());
        assertEquals(filmDTO.getLength(), film.getLength(), 0);
        //assertEquals(filmDTO.getChannel(), film.getChannel());

    }

    @Test
    public void toCollectionFilmDtoTest(){
        List<Film> films = List.of(
                new Film(LocalDateTime.now(), "Sarnie zniwo", "Git produkcja", "www.fakamaka.co",
                        22, new Channel(), Set.of(new Comment()), new Category()),
        new Film(LocalDateTime.now(), "Sarnie zniwo", "Git produkcja", "www.fakamaka.co",
                22, new Channel(), Set.of(new Comment()), new Category())
        );

        List<FilmDTO> filmDTOS = filmMapper.toFilmDTO(films);
        //assertEquals(filmDTOS.get(0).getChannel());
    }
}
