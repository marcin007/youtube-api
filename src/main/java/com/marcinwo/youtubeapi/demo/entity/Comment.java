package com.marcinwo.youtubeapi.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
@Entity
public class Comment extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String content;
    private int likes;
    private int dislikes;

    @OneToMany(mappedBy = "comment")
    private Set<Reply> replySet;



}
