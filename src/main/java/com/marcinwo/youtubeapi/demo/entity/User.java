package com.marcinwo.youtubeapi.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;

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

    @CreationTimestamp
    private LocalDateTime registerDate;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Channel> Channels = new HashSet<>();

}
