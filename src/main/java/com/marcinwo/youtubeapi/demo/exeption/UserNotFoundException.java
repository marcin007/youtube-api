package com.marcinwo.youtubeapi.demo.exeption;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
