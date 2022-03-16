package com.alkemy.disneyWorld.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@Getter
@Setter
@SQLDelete(sql = "UPDATE movie SET deleted = true WHERE id=?" )
@Where(clause = "deleted=false")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String image;
    private Integer rating;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy,MM,dd")
    private LocalDate creationDate;

    private Boolean deleted = Boolean.FALSE;


    @ManyToMany(
            mappedBy = "movies",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })

    private List<CharacterEntity> characters = new ArrayList<>();


    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    public void addCharacter(CharacterEntity characterEntity) {
        this.characters.add(characterEntity);
    }

    public void addGenre(GenreEntity genreEntity) {
        this.genre = genreEntity;
    }

    public void removeCharacter(CharacterEntity characterEntity) {
        this.characters.remove(characterEntity);
    }

}
