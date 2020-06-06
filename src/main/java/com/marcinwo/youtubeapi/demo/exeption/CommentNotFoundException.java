package com.marcinwo.youtubeapi.demo.exeption;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(String s){
        super(s);
    }
}
