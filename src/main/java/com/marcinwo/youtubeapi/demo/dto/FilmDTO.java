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
public class FilmDTO {

    private LocalDateTime uploadDate;
    private String title;
    private String description;
    private String url;
    private double length;
    private String channel;
}
