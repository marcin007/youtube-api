package com.marcinwo.youtubeapi.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private String id;
    private Long filmId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String content;
    private int likes;
    private int dislikes;

}