package com.marcinwo.youtubeapi.demo;

import com.marcinwo.youtubeapi.demo.dto.UserDTO;
import com.marcinwo.youtubeapi.demo.entity.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ExampleData {

    private ExampleData() {
    }

    public static User user() {
        return new User("Adam", "Kowalski", "akowalski", "pass", new HashSet<>(), new HashSet<>(), new HashSet<>()
        );
    }

    public static UserDTO userDTO() {
        return new UserDTO(1L, "AdamDTO", "KowalskiDTO", "akowalskiDTO", "12345");
    }

    public static Film film() {
        return new Film(
                LocalDateTime.now(), "Matrix", "costam", "url", 120,
                new Channel("channel1", "channel desc", new User(), new HashSet<>()), new HashSet<>(), new Category()
        );
    }

    public static Channel channel1() {
        return new Channel("channel1", "channel desc", user(), new HashSet<>());
    }

    public static Channel channel2() {
        return new Channel("channel2", "channel desc", user(), new HashSet<>());
    }

    public static Channel channel3() {
        return new Channel("channel3", "channel desc", user(), new HashSet<>());
    }

    public static Category categoryThriller() {
        return new Category("thriller");
    }

    public static Category categoryComedy() {
        return new Category("comedy");
    }

    public static Comment comment() {
        return new Comment(
                new Film(LocalDateTime.now(), "Matrix", "costam", "url", 120,
                    new Channel("channel1", "channel desc", user(), new HashSet<>()), new HashSet<>(), new Category()),
                new User(
                        "Adam", "Kowalski",
                        "akowalski", "pass",
                        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                LocalDateTime.now(),
                LocalDateTime.now(),
                "comment content",
                44,
                55,
                Set.of(new Reply()));
    }

    public static Comment comment2() {
        return new Comment(
                new Film(),
                new User(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                "comment content",
                44,
                55,
                Set.of(new Reply()));
    }


    public static Reply reply() {
        return new Reply(
                new User("Adam", "Kowalski", "akowalski", "pass", new HashSet<>(), new HashSet<>(), new HashSet<>()),
                new Comment(new Film(LocalDateTime.now(), "Matrix", "costam", "url", 120, new Channel("channel1", "channel desc", new User(), new HashSet<>()), new HashSet<>(), new Category()),
                        new User("Adam", "Kowalski", "akowalski", "pass", new HashSet<>(), new HashSet<>(), new HashSet<>()), LocalDateTime.now(), LocalDateTime.now(),
                        "comment content", 44, 55, Set.of(new Reply())),
                "reply content", LocalDateTime.now(), LocalDateTime.now(), 44, 33);
    }
}
