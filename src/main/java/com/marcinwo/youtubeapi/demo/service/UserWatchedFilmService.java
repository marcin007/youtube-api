package com.marcinwo.youtubeapi.demo.service;

import com.marcinwo.youtubeapi.demo.entity.UserWatchedFilm;
import com.marcinwo.youtubeapi.demo.repository.UserWatchedFilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserWatchedFilmService {

    private UserWatchedFilmRepository userWatchedFilmRepository;

    @Autowired
    public UserWatchedFilmService(UserWatchedFilmRepository userWatchedFilmRepository) {
        this.userWatchedFilmRepository = userWatchedFilmRepository;
    }

    public List<UserWatchedFilm> getWatchedFilms(){
        return userWatchedFilmRepository.findAll();
    }

    //TODO Jak kto zrobić? Czemu nie działa?
    public List<UserWatchedFilm> getWatchedFilmsByUserId(Long id){
        return userWatchedFilmRepository.getUserWatchedFilmsById(id);
    }

    public UserWatchedFilm postWatchedFilm(UserWatchedFilm userWatchedFilm){
        return userWatchedFilmRepository.save(userWatchedFilm);
    }

    //TODO Jak kto zrobić? Czemu nie działa?
    public void deleteWatchedFilmsByUserId(Long id){
        userWatchedFilmRepository.deleteUserWatchedFilmsByUserId(id);
    }
}
