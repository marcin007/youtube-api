package com.marcinwo.youtubeapi.demo.repository;

import com.marcinwo.youtubeapi.demo.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

}
