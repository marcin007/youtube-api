package com.marcinwo.youtubeapi.demo.service;

import com.marcinwo.youtubeapi.demo.entity.UserWatchedFilm;
import com.marcinwo.youtubeapi.demo.repository.UserWatchedFilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public List<UserWatchedFilm> getWatchedFilmsByUserId(Long id){
        return userWatchedFilmRepository.findAllByUser_Id(id);
    }

    public UserWatchedFilm postWatchedFilm(UserWatchedFilm userWatchedFilm){
        return userWatchedFilmRepository.save(userWatchedFilm);
    }


    public void deleteWatchedFilmsByUserId(Long id){
        userWatchedFilmRepository.deleteById(id);
    }
}
