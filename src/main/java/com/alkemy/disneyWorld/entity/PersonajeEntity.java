package com.alkemy.disneyWorld.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "personaje")
@Getter
@Setter

public class PersonajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String imagen;
    private Integer edad;
    private Integer peso; //kg
    private String historia;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "pelicula_personaje",                                  //crea tabla intermedia que relaciona los id de cada entidad
            joinColumns = @JoinColumn(name = "personaje_id"),             //lado de la tabla owning side
            inverseJoinColumns = @JoinColumn(name = "pelicula_id")                              //inverso del owning side
    )
    private Set<PeliculaSerieEntity> peliculasSeries;                     //Personaje es de "owning side"


}
