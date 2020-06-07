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
public class ReplyDTO {


    private Long userId;
    private Long commentId;
    private String content;
    private LocalDateTime createdAt;
    private int likes;
    private int dislikes;

    public ReplyDTO(Long userId, Long commentId, String content, int likes, int dislikes) {
        this.userId = userId;
        this.commentId = commentId;
        this.content = content;
        this.likes = likes;
        this.dislikes= dislikes;
    }
}
