package com.alkemy.disneyWorld.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "characters")
@Getter
@Setter
@SQLDelete(sql = "UPDATE character SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Entity
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String image;
    private Integer age;
    private Integer weight; //kg
    private String history;

    //soft delete
    private Boolean deleted = Boolean.FALSE;


    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "movie_character",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<MovieEntity> movies = new ArrayList<>();


    public void addMovie(MovieEntity movie) {
        this.movies.add(movie);
    }

    public void deleteMovie(MovieEntity movie) {
        this.movies.remove(movie);
    }

}