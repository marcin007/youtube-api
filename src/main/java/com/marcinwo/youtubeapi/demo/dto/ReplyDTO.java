package com.marcinwo.youtubeapi.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReplyDTO {

    private Long id;
    private Long userId;
    private Long commentId;
    private String content;
    private LocalDateTime createdAt;
    private int likes;
    private int dislikes;

    public ReplyDTO(Long userId, Long commentId, String content,LocalDateTime createdAt, int likes, int dislikes) {
        this.userId = userId;
        this.commentId = commentId;
        this.createdAt = createdAt;
        this.content = content;
        this.likes = likes;
        this.dislikes= dislikes;
    }
    public ReplyDTO(Long userId, Long commentId, String content, int likes, int dislikes) {
        this.userId = userId;
        this.commentId = commentId;
        this.content = content;
        this.likes = likes;
        this.dislikes= dislikes;
    }

    public ReplyDTO(Long id, Long userId, Long commentId, String content, LocalDateTime createdAt, int likes, int dislikes) {
        this.id = id;
        this.userId = userId;
        this.commentId = commentId;
        this.content = content;
        this.createdAt = createdAt;
        this.likes = likes;
        this.dislikes = dislikes;
    }
    public ReplyDTO(Long id, Long userId, Long commentId, String content, int likes, int dislikes) {
        this.id = id;
        this.userId = userId;
        this.commentId = commentId;
        this.content = content;
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
