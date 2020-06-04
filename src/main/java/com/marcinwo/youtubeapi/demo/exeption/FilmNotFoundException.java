package com.marcinwo.youtubeapi.demo.exeption;

public class FilmNotFoundException extends RuntimeException {

    public FilmNotFoundException(String message) {
        super(message);
    }
}
