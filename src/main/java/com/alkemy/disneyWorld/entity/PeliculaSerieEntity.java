package com.alkemy.disneyWorld.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pelicula_o_serie")
@Getter
@Setter

public class PeliculaSerieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String titulo;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    private Integer calificacion;

    @ManyToMany(
            mappedBy = "peliculasSeries",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private List<PersonajeEntity> personajes;

    /*
    //La relacoion entre peliculas y generos ManyToOne bidireccional

    @ManyToOne(
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
            })                                  //Este lo considero el owning side de la relacion.
    @JoinColumn(name = "id_genero")             //Mi tabla pelicula_o_serie va a tener una columna con Fk el
    private GeneroEntity genero;                //id del genero al cual pertenece
     */
}
