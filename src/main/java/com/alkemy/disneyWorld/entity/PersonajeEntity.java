package com.alkemy.disneyWorld.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "personaje")
@Getter
@Setter
@SQLDelete(sql = "UPDATE personaje SET deleted = true WHERE id=?" )
@Where(clause = "deleted=false")
public class PersonajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String imagen;
    private Integer edad;
    private Integer peso; //kg
    private String historia;
    private boolean deleted = Boolean.FALSE;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "pelicula_personaje",                                  //Crea tabla intermedia que relaciona los id de cada entidad
            joinColumns = @JoinColumn(name = "personaje_id"),             //lado de la tabla owning side.
            inverseJoinColumns = @JoinColumn(name = "pelicula_id")        //Inverso del owning side.
    )
    private Set<PeliculaSerieEntity> peliculasSeries;                     //Esta entidad es el "owning side".


}