package com.alkemy.disneyWorld.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genre")
@Getter
@Setter
@SQLDelete(sql = "UPDATE genre SET deleted = true WHERE id=?" )
@Where(clause = "deleted=false")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String image;
    private Boolean deleted = Boolean.FALSE;

    @OneToMany(
            mappedBy = "genre",                                  //El mapping de la relacion entre las peliculas y el genero lo realiza peliculas a
            fetch = FetchType.LAZY,                               //traves de dejar la relacion plasmada en una columna de la tabla con el id de genero(Fk).
            cascade = {
                    CascadeType.PERSIST,                          //Fetch podria ser EAGER eager dado que la lista de peliculas probablemente no sea muy larga
                    CascadeType.MERGE                             //y no es necesario recibir los datos on-demand dado que con pocos datos la
            })                                                    //perforamance se va a ver poco afectada.

    private List<MovieEntity> movies;

}
