package com.marcinwo.youtubeapi.demo.exception;

public class ChannelNotFoundException extends RuntimeException{
    public ChannelNotFoundException(String message) {
        super(message);
    }
}
