package com.marcinwo.youtubeapi.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
@Entity
public class Category extends AbstractEntity {

//    @OneToMany(mappedBy = "category")
//    private Set<Film> films = new HashSet<>();

    private String name;

    public Category(Long id, String name) {
        super(id);
        this.name = name;
    }
}
