package com.marcinwo.youtubeapi.demo.dto;

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

    private Long userId;
    private Long filmId;
    private BigDecimal timeSpentForWatching;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
