package com.marcinwo.youtubeapi.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "films")
public class Film extends AbstractEntity {

    private LocalDateTime uploadDate;
    private String title;
    private String description;
    private String url;
    private double length;
    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;
}
