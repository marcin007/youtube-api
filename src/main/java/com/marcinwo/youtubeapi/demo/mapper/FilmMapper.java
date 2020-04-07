package com.marcinwo.youtubeapi.demo.mapper;

import com.marcinwo.youtubeapi.demo.dto.FilmDTO;
import com.marcinwo.youtubeapi.demo.entity.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FilmMapper {

    @Mappings({
            @Mapping(target = "channel", source = "channel.name")
    })
    FilmDTO toFilmDTO(Film film);

    List<FilmDTO> toFilmDTO(Collection<Film> films);
}
