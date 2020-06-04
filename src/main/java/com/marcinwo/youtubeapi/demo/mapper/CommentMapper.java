package com.marcinwo.youtubeapi.demo.mapper;

import com.marcinwo.youtubeapi.demo.dto.CommentDTO;
import com.marcinwo.youtubeapi.demo.entity.Comment;
import com.marcinwo.youtubeapi.demo.entity.Film;
import com.marcinwo.youtubeapi.demo.entity.User;
import com.marcinwo.youtubeapi.demo.exeption.FilmNotFoundException;
import com.marcinwo.youtubeapi.demo.exeption.UserNotFoundException;
import com.marcinwo.youtubeapi.demo.repository.FilmRepository;
import com.marcinwo.youtubeapi.demo.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    private UserRepository userRepository;
    private FilmRepository filmRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setFilmRepository(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Mappings({
            @Mapping(target = "filmId", source = "film.id"),
            @Mapping(target = "userId", source = "user.id")
    })
    public abstract CommentDTO toCommentDTO(Comment comment);

    public abstract List<CommentDTO> toCommentDTO(Collection<Comment> comments);

    public Comment toCommentEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();

        User user = userRepository.findById(commentDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        comment.setUser(user);

        Film film = filmRepository.findById(commentDTO.getFilmId())
                .orElseThrow(() -> new FilmNotFoundException("Film not found"));
        comment.setFilm(film);

        comment.setLikes(commentDTO.getLikes());
        comment.setDislikes(commentDTO.getDislikes());

        // TODO: 03.06.2020 dokonczyc

        return comment;
    }

}
