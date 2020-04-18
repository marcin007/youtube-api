package com.marcinwo.youtubeapi.demo.repository;

import com.marcinwo.youtubeapi.demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByFilm_Id(Long id);
}

