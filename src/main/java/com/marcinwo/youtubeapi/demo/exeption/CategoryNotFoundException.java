package com.marcinwo.youtubeapi.demo.exeption;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
