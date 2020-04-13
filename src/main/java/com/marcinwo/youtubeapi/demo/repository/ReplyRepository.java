package com.marcinwo.youtubeapi.demo.repository;

import com.marcinwo.youtubeapi.demo.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
