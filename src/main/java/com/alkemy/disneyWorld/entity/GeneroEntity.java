package com.alkemy.disneyWorld.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genero")
@Getter
@Setter
public class GeneroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;

    private String imagen;



    //La relacion entre genero y peliculas @OnetoMany unidireccional:

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "genero_id")                                        //una una relacion @OneToMany unidireccional si no especifico el joincolumn,
                                                                          // jpa crea una tabla intermedia para relacionar los ids y usa exceso de queeries
    private List<PeliculaSerieEntity> peliculasSeries = new ArrayList<>();



/*
    //Relacion entre genero y peliculas @OnetoMany bidireccional:

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "genero")               // Fetch en eager dado que la lista de peliculas probablemente no sea muy larga
    private List<PeliculaSerieEntity> peliculasSeries;                     // y no es necesario recibir los datos on-demand dado que con pocos datos la
                                                                           // perforamance se va a ver poco afectada.
                                                                           // El mapping de la relacion entre las peliculas y el genero lo realiza peliculas a
                                                                           // traves de dejar la relacion plasmada en una columna de la tabla con el id de genero(Fk)
 */

}
