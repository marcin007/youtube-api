package com.marcinwo.youtubeapi.demo.mapper;


import com.marcinwo.youtubeapi.demo.dto.UserWatchedFilmDTO;
import com.marcinwo.youtubeapi.demo.entity.UserWatchedFilm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserWatchedFilmMapper {

    @Mappings({
            @Mapping(target = "film", source = "film.title"),
            @Mapping(target = "user", source = "user.userName")
    })
    UserWatchedFilmDTO toUserWatchedFilmDTO(UserWatchedFilm userWatchedFilm);
    List<UserWatchedFilmDTO> toUserWatchedFilmDTO(Collection<UserWatchedFilm> watchedFilms);

    @Mappings({
            @Mapping(target = "film.title", source = "film"),
            @Mapping(target = "user.userName", source = "user")
    })
    UserWatchedFilm toUserWatchedFilmEntity(UserWatchedFilmDTO userWatchedFilmDTO);
}
