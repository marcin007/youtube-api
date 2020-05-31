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

    public ReplyDTO(Long userId, Long commentId, String content) {
        this.userId = userId;
        this.commentId = commentId;
        this.content = content;
    }
}
