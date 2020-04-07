package com.marcinwo.youtubeapi.demo.controller;

import com.marcinwo.youtubeapi.demo.dto.FilmDTO;
import com.marcinwo.youtubeapi.demo.entity.Film;
import com.marcinwo.youtubeapi.demo.mapper.FilmMapper;
import com.marcinwo.youtubeapi.demo.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilmController {

    private FilmService filmService;
    private FilmMapper filmMapper;

    @Autowired
    public FilmController(FilmService filmService, FilmMapper filmMapper){
        this.filmService = filmService;
        this.filmMapper = filmMapper;
    }

    @GetMapping("/film/{id}")
    public FilmDTO getFilm(@PathVariable Long id){
        return filmMapper.toFilmDTO(filmService.getFilm(id));
    }

    @GetMapping("/films")
    public List<FilmDTO> getFilms(){
        return filmMapper.toFilmDTO(filmService.getFilms());
    }
}
