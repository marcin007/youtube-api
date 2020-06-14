package com.marcinwo.youtubeapi.demo.exeption;

public class ChannelNotFoundException extends RuntimeException{
    public ChannelNotFoundException(String message) {
        super(message);
    }
}
