package com.marcinwo.youtubeapi.demo.exception;


import com.marcinwo.youtubeapi.demo.exception.CategoryNotFoundException;
import com.marcinwo.youtubeapi.demo.exception.ChannelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(com.marcinwo.youtubeapi.demo.exception.UserNotFoundException.class)
    public ResponseEntity<com.marcinwo.youtubeapi.demo.exception.ApiError> handlerUserNotFoundException (com.marcinwo.youtubeapi.demo.exception.UserNotFoundException e, WebRequest webRequest){
        return new ResponseEntity<>(new com.marcinwo.youtubeapi.demo.exception.ApiError("User not found.", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<com.marcinwo.youtubeapi.demo.exception.ApiError> handlerCategoryNotFoundException(CategoryNotFoundException e, WebRequest webRequest){
        return new ResponseEntity<>(new com.marcinwo.youtubeapi.demo.exception.ApiError("Category not found.", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ChannelNotFoundException.class)
    public ResponseEntity<com.marcinwo.youtubeapi.demo.exception.ApiError> handlerChannelNotFoundException(CategoryNotFoundException e, WebRequest webRequest){
        return new ResponseEntity<>(new com.marcinwo.youtubeapi.demo.exception.ApiError("Channel not found.", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }


}
