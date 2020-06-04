package com.marcinwo.youtubeapi.demo;

import com.marcinwo.youtubeapi.demo.dto.UserDTO;
import com.marcinwo.youtubeapi.demo.entity.Category;
import com.marcinwo.youtubeapi.demo.entity.Channel;
import com.marcinwo.youtubeapi.demo.entity.Film;
import com.marcinwo.youtubeapi.demo.entity.User;

import java.time.LocalDateTime;
import java.util.HashSet;

public class ExampleData {

    private ExampleData() {}

    public static User user() {
        return new User(
                "Adam", "Kowalski",
                "akowalski", "pass",
                new HashSet<>(), new HashSet<>(), new HashSet<>()
        );
    }

    public static UserDTO userDTO(){
        return new UserDTO(1L, "AdamDTO", "KowalskiDTO","akowalskiDTO", "12345");
    }

    public static Film film() {
        return new Film(
                LocalDateTime.now(), "Matrix", "costam", "url", 120,
                channel(), new HashSet<>(), categoryThriller()
        );
    }

    public static Channel channel() {
        return new Channel("channel1", "channel desc", user(), new HashSet<>());
    }

    public static Category categoryThriller() {
        return new Category("thriller");
    }

    public static Category categoryComedy() {
        return new Category("comedy");
    }
}
