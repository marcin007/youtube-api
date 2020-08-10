package com.marcinwo.youtubeapi.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;

//    @CreationTimestamp
//    private LocalDateTime registerDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Channel> channels = new HashSet<>();

    @OneToMany(mappedBy = "user") // default Lazy
    private Set<UserWatchedFilm> watchedFilms = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Reply> replies = new HashSet<>();

    public User(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }
}
