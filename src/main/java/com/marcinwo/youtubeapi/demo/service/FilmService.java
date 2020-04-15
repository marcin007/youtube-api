package com.marcinwo.youtubeapi.demo.service;

import com.marcinwo.youtubeapi.demo.entity.Film;
import com.marcinwo.youtubeapi.demo.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    private FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository){
        this.filmRepository = filmRepository;
    }

    public List<Film> getFilms(){
        return filmRepository.findAll();
    }

    public Film getFilm(Long id){
        return filmRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cant find."));
    }

    public List<Film> getFilmsByChannelId(Long id){
        return filmRepository.findAllByChannel_Id(id);
    }

}
