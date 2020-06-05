package com.marcinwo.youtubeapi.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reply extends AbstractEntity {


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int likes;
    private int dislikes;



}
