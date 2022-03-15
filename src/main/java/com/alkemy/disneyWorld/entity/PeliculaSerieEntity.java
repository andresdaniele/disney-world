package com.alkemy.disneyWorld.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pelicula_o_serie")
@Getter
@Setter
@SQLDelete(sql = "UPDATE pelicula_o_serie SET deleted = true WHERE id=?" )
@Where(clause = "deleted=false")
public class PeliculaSerieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String titulo;

    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy,MM,dd")
    private LocalDate fechaCreacion;

    private Integer calificacion;

    private boolean deleted = Boolean.FALSE;

    @ManyToMany(
            mappedBy = "peliculasSeries",
            cascade = {                                               //PERSIST: las operaciones en la entidad padre se propagan tambien a la hija.
                    CascadeType.PERSIST,
                    CascadeType.MERGE                                 //MERGE: al modificar un objeto de la base de dato, esta actualizcion persiste
            })                                                        //a las demas entides relacionadas.
    private List<PersonajeEntity> personajes;


    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })                                                //Esta entidad la considero el owning side de la relacion con genero.
    @JoinColumn(name = "id_genero")                           //Mi tabla pelicula_o_serie va a tener una columna con Fk el
    private GeneroEntity genero;                              //id del genero al cual pertenece.

}
