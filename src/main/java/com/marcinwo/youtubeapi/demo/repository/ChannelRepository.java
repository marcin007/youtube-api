package com.marcinwo.youtubeapi.demo.repository;

import com.marcinwo.youtubeapi.demo.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    // JPQL - java persistence query language
    @Query(value = "select a.channels From User a where a.id = ?1" )
    List<Channel> findAllByUser_Id(Long id);

//    @Modifying
//    @Query(value = "INSERT INTO channels(name, description,user_id) values(:name, :description, :id)", nativeQuery = true)
//    Channel postUserChannelById(@Param("name") Channel channel.name, Channel channel.description);

    @Query(value = "INSERT INTO channels(user_id, name, description,) values(?1, ?2, ?3)")
    Channel postUserChannelById(Long id, Channel channel);

                                            // channel.name -> do parametru 2
                                            // channel.description > do parametru 3


}
