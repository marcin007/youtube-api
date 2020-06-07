package com.marcinwo.youtubeapi.demo.exeption;

public class UserWatchedFilmNotFoundException extends RuntimeException {

    public UserWatchedFilmNotFoundException(String s){
        super(s);
    }
}
