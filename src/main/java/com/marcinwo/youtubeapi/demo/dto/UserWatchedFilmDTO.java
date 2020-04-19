package com.marcinwo.youtubeapi.demo.dto;

import com.marcinwo.youtubeapi.demo.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWatchedFilmDTO {

    private String film; //TODO id
    private String user;
    private BigDecimal timeSpentForWatching;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
