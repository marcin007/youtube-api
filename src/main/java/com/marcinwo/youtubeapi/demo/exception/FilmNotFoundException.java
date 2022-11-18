package com.marcinwo.youtubeapi.demo.exception;

public class FilmNotFoundException extends RuntimeException {

    public FilmNotFoundException(String message) {
        super(message);
    }
}
