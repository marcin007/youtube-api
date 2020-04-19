package com.marcinwo.youtubeapi.demo.repository;

import com.marcinwo.youtubeapi.demo.entity.UserWatchedFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserWatchedFilmRepository extends JpaRepository<UserWatchedFilm, Long> {

    List<UserWatchedFilm> findAllByUser_Id(Long id);

    void deleteAllByUser_Id(Long id);
}
