package com.alkemy.disneyWorld.dto;

import com.alkemy.disneyWorld.entity.GeneroEntity;
import com.alkemy.disneyWorld.entity.PersonajeEntity;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PeliculaSerieDTO {

    private Long id;
    private String imagen;
    private String titulo;
    private LocalDate fechaCreacion;
    private Integer calificacion;
    private List<PersonajeDTO> personajes;
    private GeneroEntity genero;
}
